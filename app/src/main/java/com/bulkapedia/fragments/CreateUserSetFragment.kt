package com.bulkapedia.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.activities.GearActivityDialog
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.CreateUserSetFragmentBinding
import com.bulkapedia.gears.*
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
                    val userSet = UserSet(editSet!!.setId,
                        MAIN.prefs.getNickname()!!, editSet!!.hero,
                        mapOf(GearCell.HEAD to headIcon, GearCell.BODY to bodyIcon,
                            GearCell.ARM to armIcon, GearCell.LEG to legIcon,
                            GearCell.DECOR to decorIcon, GearCell.DEVICE to deviceIcon),
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
                    sumEffects()
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
                GearCell.HEAD -> headIcon = gear
                GearCell.BODY -> bodyIcon = gear
                GearCell.ARM -> armIcon = gear
                GearCell.LEG -> legIcon = gear
                GearCell.DECOR -> decorIcon = gear
                else -> deviceIcon = gear
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
            val gears = when (btn.id) {
                bind.customHeadBtn.id -> GearsList.headIcon.apply {
                    val gears = getHeroSetItems(hero, GearCell.HEAD)
                    if (!containsAll(gears))
                        addAll(gears)
                }
                bind.customBodyBtn.id -> GearsList.bodyIcons.apply {
                    val gears = getHeroSetItems(hero, GearCell.BODY)
                    if (!containsAll(gears))
                        addAll(gears)
                }
                bind.customArmBtn.id -> GearsList.armIcons.apply {
                    val gears = getHeroSetItems(hero, GearCell.ARM)
                    if (!containsAll(gears))
                        addAll(gears)
                }
                bind.customLegBtn.id -> GearsList.legIcons.apply {
                    val gears = getHeroSetItems(hero, GearCell.LEG)
                    if (!containsAll(gears))
                        addAll(gears)
                }
                bind.customDecorBtn.id -> GearsList.decorIcons.apply {
                    val gears = getHeroSetItems(hero, GearCell.DECOR)
                    if (!containsAll(gears))
                        addAll(gears)
                }
                bind.customDeviceBtn.id -> GearsList.deviceIcons.apply {
                    val gears = getHeroSetItems(hero, GearCell.DEVICE)
                    if (!containsAll(gears))
                        addAll(gears)
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
            btn.setImageResource(icon)
            btn.setOnClickListener {
                clickedBtn = btn
                val gearIcons: IntArray = gears.map { it.icon }.toIntArray()
                val intent = Intent(context, GearActivityDialog::class.java).apply {
                    putExtra("gearIcons", gearIcons)
                    putExtra("gearType", gearType)
                }
                launcher.launch(intent)
            }
        }
        sumEffects()
    }

    private fun sumEffects() {
        val customs = listOf(
            headIcon, bodyIcon,
            armIcon, legIcon,
            decorIcon, deviceIcon
        )
        val icons = mutableListOf<Int>()
        val effectsMap = mutableMapOf<Effect, Int>()
        var i = 0
        while (i < 6) {
            val icon = customs[i]
            if (icon != 0) icons.add(icon)
            i++
        }
        icons.forEach { icon ->
            val index = GearsList.allGears.map(Gear::icon).indexOf(icon)
            val effects = GearsList.allGears[index].effects
            //
            effects.forEach { e ->
                val eDesc = effectsMap.map { it.key.description }
                if (eDesc.contains(e.description)) {
                    val eff = effectsMap.map { it.key }[eDesc.indexOf(e.description)]
                    effectsMap.replace(eff, eff.number + e.number)
                } else effectsMap += e to e.number
            }
        }
        val gears = icons.map { icon ->
            val index = GearsList.allGears.map(Gear::icon).indexOf(icon)
            GearsList.allGears[index]
        }
        val pgCount = gears.filter { it.gearSet == GearSet.PERSONAL }.size
        val effects = GearsList.getEffectsFromSets(gears) + PersonalGears.getPersonalGears(args.heroModel.hero, pgCount)
        effects.forEach { e ->
            val eDesc = effectsMap.map { it.key.description }
            if (eDesc.contains(e.description)) {
                val eff = effectsMap.map { it.key }[eDesc.indexOf(e.description)]
                effectsMap.replace(eff, eff.number + e.number)
            } else effectsMap += e to e.number
        }

        val buffer = StringBuilder()
        effectsMap.forEach { (effect, i) ->
            if (i > 0) buffer.append("+")
            buffer.append(i)
            if (effect.percent) buffer.append("%")
            buffer.append(" ${getString(effect.description)}\n")
        }
        bind.effectsTV.text = buffer.toString()
        bind.effectsTV.invalidate()
    }

    private fun getHeroSetItems(hero: Hero, cell: GearCell) : MutableList<Gear> {
        return mutableListOf(
            GearsList.getSetsGears(hero)[cell]!!,
            hero.getPersonalGears()[cell]!!
        )
    }

}