package com.bulkapedia.views.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.views.activities.GearActivityDialog
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.CreateUserSetFragmentBinding
import com.bulkapedia.data.gears.*
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.labels.Ranks
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.utils.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt

class CreateUserSetFragment : Fragment() {

    private lateinit var bind: CreateUserSetFragmentBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private val args: CreateUserSetFragmentArgs by navArgs()

    private lateinit var database: FirebaseFirestore

    private lateinit var clickedBtn: ImageView
    private var editSet: UserSet? = null
    private var headIcon: Int = 0
    private var bodyIcon: Int = 0
    private var armIcon: Int = 0
    private var legIcon: Int = 0
    private var decorIcon: Int = 0
    private var deviceIcon: Int = 0
    private val rankMap = mutableMapOf<Int, Ranks>()

    private var showingBuffs = false

    private val effectsMap = mutableMapOf<Effect, Int>()
    private val percentEffectsMap = mutableMapOf<Effect, Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = CreateUserSetFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.firestore
        editSet = args.set
        args.heroModel.apply {
            bind.heroIcon.setImageResource(heroIcon)
            bind.actionBarInclude.actionBar.title = getString(heroName)
            bind.actionBarInclude.actionBar.setNavigationIcon(R.drawable.backspace)
            bind.actionBarInclude.actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            initCustomButtons(hero)
            bind.cancelButton.setOnClickListener {
                findNavController().navigateUp()
            }
            bind.saveButton.setOnClickListener {
                if (editSet != null) {
                    val userSet = UserSet(
                        editSet!!.setId,
                        MAIN.prefs.getNickname()!!, editSet!!.hero,
                        mapOf(
                            GearCell.HEAD to gearResourceToString(headIcon), GearCell.BODY to gearResourceToString(bodyIcon),
                            GearCell.ARM to gearResourceToString(armIcon), GearCell.LEG to gearResourceToString(legIcon),
                            GearCell.DECOR to gearResourceToString(decorIcon), GearCell.DEVICE to gearResourceToString(deviceIcon)
                        ),
                        editSet!!.likes, editSet!!.userLikeIds
                    )
                    Database().addUserSet(userSet)
                } else {
                    val userSet = mapOf(
                        "author" to MAIN.prefs.getNickname(),
                        "hero" to heroIcon,
                        "head" to headIcon, "body" to bodyIcon,
                        "arm" to armIcon, "leg" to legIcon,
                        "decor" to decorIcon, "device" to deviceIcon,
                        "likes" to 0,
                        "user_like_ids" to listOf<String>()
                    )
                    database.collection("sets").add(userSet)
                }
                // return to HeroFragment
                findNavController().navigateUp()
            }
        }
        bind.showHideBuffs.setOnClickListener {
            showingBuffs = !showingBuffs
            if (!showingBuffs) {
                bind.effectsTV.text = ""
                bind.showHideBuffs.setText(R.string.show_buffs)
            } else {
                bind.showHideBuffs.setText(R.string.hide_buffs)
                sumEffects()
            }
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                if (data != null) {
                    val icon = data.getIntExtra("gear", 0)
                    clickedBtn.setImageResource(icon)
                    when (data.getStringExtra("gearType")) {
                        "head" -> headIcon = icon
                        "body" -> bodyIcon = icon
                        "arm" -> armIcon = icon
                        "leg" -> legIcon = icon
                        "decor" -> decorIcon = icon
                        else -> deviceIcon = icon
                    }
                    rankMap += when (data.getIntExtra("rank", 0)) {
                        R.id.common_tv -> icon to Ranks.COMMON
                        R.id.rare_tv -> icon to Ranks.RARE
                        R.id.epic_tv -> icon to Ranks.EPIC
                        R.id.legendary_tv -> icon to Ranks.LEGENDARY
                        R.id.mythic_tv -> icon to Ranks.MYTHIC
                        R.id.supreme_tv -> icon to Ranks.SUPREME
                        R.id.ultimate_tv -> icon to Ranks.ULTIMATE
                        R.id.celestial_tv -> icon to Ranks.CELESTIAL
                        else -> icon to Ranks.STELLAR
                    }
                    if (showingBuffs) sumEffects()
                }
            }
        }
    }

    private fun initCustomButtons(hero: Hero) {
        val icons = listOf(
            R.drawable.empty_head, R.drawable.empty_body,
            R.drawable.empty_arm, R.drawable.empty_leg,
            R.drawable.empty_decor, R.drawable.empty_device
        )
        val customs = listOf(
            bind.customHeadBtn, bind.customBodyBtn,
            bind.customArmBtn, bind.customLegBtn,
            bind.customDecorBtn, bind.customDeviceBtn
        )
        editSet?.gears?.forEach { (cell, gear) ->
            when (cell) {
                GearCell.HEAD -> headIcon = gearStringToResource(gear)
                GearCell.BODY -> bodyIcon = gearStringToResource(gear)
                GearCell.ARM -> armIcon = gearStringToResource(gear)
                GearCell.LEG -> legIcon = gearStringToResource(gear)
                GearCell.DECOR -> decorIcon = gearStringToResource(gear)
                else -> deviceIcon = gearStringToResource(gear)
            }
        }
        val gearsIcons = listOf(
            headIcon, bodyIcon,
            armIcon, legIcon,
            decorIcon, deviceIcon
        )
        customs.forEachIndexed { i, btn ->
            val icon = if (gearsIcons[i] != 0) gearsIcons[i]
            else icons[i]
            val buffer = getDefaultGearsByBtn(btn.id)
            val insertedGears = mutableListOf<Gear>()
            btn.setImageResource(icon)
            btn.setOnClickListener {
                clickedBtn = btn
                val gears = when (btn.id) {
                    bind.customHeadBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.HEAD)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    bind.customBodyBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.BODY)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    bind.customArmBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.ARM)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    bind.customLegBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.LEG)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    bind.customDecorBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.DECOR)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    bind.customDeviceBtn.id -> {
                        val gears = getHeroSetItems(hero, GearCell.DEVICE)
                        if (!buffer.containsAll(gears)) {
                            buffer.addAll(gears)
                            insertedGears.addAll(gears)
                        }
                        buffer
                    }
                    else -> listOf()
                }
                val gearType = when (btn.id) {
                    bind.customHeadBtn.id -> "head"
                    bind.customBodyBtn.id -> "body"
                    bind.customArmBtn.id -> "arm"
                    bind.customLegBtn.id -> "leg"
                    bind.customDecorBtn.id -> "decor"
                    else -> "device"
                }
                val gearIcons: IntArray = gears.map { it.icon }.toIntArray()
                val intent = Intent(context, GearActivityDialog::class.java).apply {
                    putExtra("gearIcons", gearIcons)
                    putExtra("gearType", gearType)
                }
                buffer.removeAll(insertedGears)
                launcher.launch(intent)
            }
        }
    }

    private fun sumEffects() {
        effectsMap.clear()
        percentEffectsMap.clear()
        val emptyIcons = listOf(
            R.drawable.empty_head, R.drawable.empty_body,
            R.drawable.empty_arm, R.drawable.empty_leg,
            R.drawable.empty_decor, R.drawable.empty_device
        )
        val customs = listOf(
            headIcon, bodyIcon,
            armIcon, legIcon,
            decorIcon, deviceIcon
        )
        val icons = mutableListOf<Int>()
        var i = 0
        while (i < 6) {
            val icon = customs[i]
            if (icon != 0) icons.add(icon)
            i++
        }
        val effects = mutableListOf<Effect>()
        icons.forEach { icon ->
            if (!emptyIcons.contains(icon)) {
                val index = GearsList.allGears.map(Gear::icon).indexOf(icon)
                if (rankMap[icon] != null) {
                    effects.addAll(GearsList.allGears[index].rankEffect[rankMap[icon]]!!)
                } else {
                    effects.addAll(GearsList.allGears[index].effects)
                }
            }
        }
        val gears = icons.map { icon ->
            if (emptyIcons.contains(icon)) Gear(GearSet.NONE, icon, emptyList(), emptyMap())
            else {
                val index = GearsList.allGears.map(Gear::icon).indexOf(icon)
                GearsList.allGears[index]
            }
        }
        val pgCount = gears.filter { it.gearSet == GearSet.PERSONAL }.size
        effects.addAll(
            GearsList.getEffectsFromSets(gears) +
                    PersonalGears.getPersonalGears(args.heroModel.hero, pgCount)
        )
        fillEffects(effects)
        val stats = getStats()
        val spannable = SpannableStringBuilder()
        var startGreen = 0
        var startBold = 0
        var endGreen = 0
        var endBold = 0

        stats.forEach { (eid, value) ->
            val eff = effectsMap.mapKeys { it.key.description }[eid]
            val pEff = percentEffectsMap.mapKeys { it.key.description }[eid]
            val text = effectDescToStatsName(eid).plus(": ")
            //  add text
            spannable.append(text)
            endBold += text.length
            // double to int
            val intOrDblValue = doubleToIntIfNotDouble(value) ?: value

            var number = ""

            if (eid == R.string.health_damage_effect ||
                eid == R.string.armor_damage_effect
            ) {
                spannable.append("x")
            }
            spannable.append(intOrDblValue.toString())

            if (eff != null) {
                val numberDouble = sumValueEffects(eid, intOrDblValue, eff, false).toDouble()
                number = (doubleToIntIfNotDouble(numberDouble) ?: numberDouble).toString()

                startGreen = endBold
                endGreen = startGreen

                spannable.append(" -> ").append(number).append(" (")
                if (eff > 0) {
                    spannable.append("+")
                    endGreen++
                }
                spannable.append("$eff").append(")")

                endGreen += intOrDblValue.toString().length +
                        " -> ".length + number.length + 4 + eff.toString().length

                if (pEff != null) spannable.append(" ")
                else spannable.append("\n")
            }
            if (pEff != null) {
                val percent = percentEffectsMap.map {
                    it.key.description to it.key.percent
                }.toMap()[eid]!!
                val numberDouble =
                    sumValueEffects(
                        eid,
                        if (eff != null) number.toDouble()
                        else intOrDblValue,
                        pEff, percent
                    ).toDouble()
                val oldNumber = number
                number = (doubleToIntIfNotDouble(numberDouble) ?: numberDouble).toString()

                if (eff == null) {
                    startGreen = endBold
                    endGreen = startGreen
                }
                endGreen += if (eff != null) {
                    val startIndex = startGreen + 5 + eff.toString().length
                    spannable.replace(
                        startIndex, startIndex + oldNumber.length,
                        number
                    )
                    spannable.append("(")
                    (number.length - oldNumber.length) + 1
                } else {
                    spannable.append(" -> ").append(number).append(" (")
                    " -> ".length + number.length + 2 + intOrDblValue.toString().length
                }
                if (pEff > 0) {
                    spannable.append("+")
                    endGreen++
                }
                spannable.append("$pEff")
                endGreen += pEff.toString().length
                if (percent) {
                    spannable.append("%")
                    endGreen++
                }
                spannable.append(")\n")
                endGreen += 2
            }
            if (eff == null && pEff == null) {
                endBold += intOrDblValue.toString().length
                if (eid == R.string.health_damage_effect ||
                    eid == R.string.armor_damage_effect
                ) {
                    endBold++
                }
                spannable.append("\n")
                endBold++
            }
            spannable.setSpan(
                StyleSpan(android.graphics.Typeface.BOLD),
                startBold, endBold,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (endGreen - startGreen > 0) {
                spannable.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    startGreen, endGreen,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            startGreen = endGreen
            startBold = if (startBold < endGreen) endGreen else startBold
            endBold = if (endBold < startGreen) startGreen else endBold
        }
        bind.effectsTV.setText(spannable, TextView.BufferType.SPANNABLE)
        bind.effectsTV.invalidate()
    }

    private fun getHeroSetItems(hero: Hero, cell: GearCell): MutableList<Gear> {
        return mutableListOf(
            GearsList.getSetsGears(hero)[cell]!!,
            hero.getPersonalGears()[cell]!!
        )
    }

    private fun getDefaultGearsByBtn(btn: Int): MutableList<Gear> = when (btn) {
        bind.customHeadBtn.id -> GearsList.headIcon
        bind.customBodyBtn.id -> GearsList.bodyIcons
        bind.customArmBtn.id -> GearsList.armIcons
        bind.customLegBtn.id -> GearsList.legIcons
        bind.customDecorBtn.id -> GearsList.decorIcons
        bind.customDeviceBtn.id -> GearsList.deviceIcons
        else -> mutableListOf()
    }

    private fun getStats(): MutableMap<Int, Double> {
        val stats = args.heroModel.hero.getStats()
        val map = mutableMapOf<Int, Double>()

        map[R.string.max_health_effect] = stats.maxHealth.toDouble()
        map[R.string.max_armor_effect] = stats.maxArmor.toDouble()
        map[R.string.damage_effect] = stats.damage.toDouble()
        map[R.string.visibility_effect] = stats.vision.toDouble()
        map[R.string.running_volume_effect] = stats.maxSilence.toDouble()
        map[R.string.speed_effect] = stats.maxSpeed.toDouble()
        map[R.string.speed_in_focus_effect] = stats.maxSpeedInFocus.toDouble()
        map[R.string.reloading_time_effect] = stats.reloadTime
        map[R.string.fire_rate_effect] = stats.fireRate
        map[R.string.fire_range_effect] = stats.fireRange.toDouble()
        map[R.string.fire_range_focus_effect] = stats.fireRangeInFocus.toDouble()
        map[R.string.spread_in_not_focus_effect] = stats.spreadOnStay.toDouble()
        map[R.string.spread_in_move_effect] = stats.spreadInMove.toDouble()
        map[R.string.spread_in_focus_effect] = stats.spreadInFocus.toDouble()
        map[R.string.add_patrons_effect] = stats.patrons.toDouble()
        map[R.string.piercing_power_effect] = stats.powerPenetration.toDouble()
        map[R.string.health_damage_effect] = stats.damageOnHealth
        map[R.string.armor_damage_effect] = stats.damageOnArmor
        map[R.string.piercing_effect] = stats.armorPenetration.toDouble()
        map[R.string.aiming_speed_effect] = stats.focusTime
        return map
    }

    private fun effectDescToStatsName(effectDesc: Int): String {
        val resId = when (effectDesc) {
            R.string.max_health_effect -> R.string.health
            R.string.max_armor_effect -> R.string.armor
            R.string.damage_effect -> R.string.damage
            R.string.visibility_effect -> R.string.vision
            R.string.running_volume_effect -> R.string.max_silence
            R.string.speed_effect -> R.string.max_speed
            R.string.speed_in_focus_effect -> R.string.max_speed_in_focus
            R.string.reloading_time_effect -> R.string.reloading_time
            R.string.fire_rate_effect -> R.string.fire_rate
            R.string.fire_range_effect -> R.string.fire_range
            R.string.fire_range_focus_effect -> R.string.fire_range_in_focus
            R.string.spread_in_not_focus_effect -> R.string.fire_spread
            R.string.spread_in_move_effect -> R.string.fire_spread_while_moving
            R.string.spread_in_focus_effect -> R.string.fire_spread_in_focus
            R.string.add_patrons_effect -> R.string.magazine_size
            R.string.piercing_power_effect -> R.string.piercing_power
            R.string.health_damage_effect -> R.string.health_damage_mod
            R.string.armor_damage_effect -> R.string.armor_damage_mod
            R.string.piercing_effect -> R.string.armor_penetration
            R.string.aiming_speed_effect -> R.string.aiming_time
            else -> effectDesc
        }
        return if (resId != effectDesc) getString(resId)
        else ""
    }

    private fun sumValueEffects(eff: Int, val1: Number, val2: Number, percent: Boolean): Number {
        return when (eff) {
            R.string.max_health_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.max_armor_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.damage_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.visibility_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.running_volume_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.speed_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.speed_in_focus_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.reloading_time_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.fire_rate_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.fire_range_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.fire_range_focus_effect -> {
                return if (!percent)
                    val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.spread_in_not_focus_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.spread_in_move_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.spread_in_focus_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.add_patrons_effect -> val1.toInt() + val2.toInt()
            R.string.piercing_power_effect -> val1.toInt() + val2.toInt()
            R.string.health_damage_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.armor_damage_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            R.string.piercing_effect -> {
                return if (!percent) val1.toInt() + val2.toInt()
                else val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            }
            R.string.aiming_speed_effect ->
                val1.toDouble() + (val1.toDouble() * (val2.toDouble() / 100))
            else -> 0
        }
    }

    private fun doubleToIntIfNotDouble(db: Double): Int? {
        val round = db.roundToInt()
        return if (db - round.toDouble() != 0.0)
            null
        else round
    }

    private fun fillEffects(effects: List<Effect>) {
        effects.forEach { e ->
            val eDesc = effectsMap.map { it.key.description }
            val ePDesc = percentEffectsMap.map { it.key.description }
            val percent = e.percent
            val fill: (List<Int>, MutableMap<Effect, Int>) -> Unit = { desc, map ->
                if (desc.contains(e.description)) {
                    val eff = map[e]!!
                    map.replace(e, eff + e.number)
                } else map += e to e.number
            }
            if (percent) fill(ePDesc, percentEffectsMap)
            else fill(eDesc, effectsMap)
        }
    }

}