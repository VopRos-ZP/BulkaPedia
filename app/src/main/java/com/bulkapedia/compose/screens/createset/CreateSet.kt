package com.bulkapedia.compose.screens.createset

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.GEARS_LIST
import com.bulkapedia.R
import com.bulkapedia.compose.screens.createset.selectgear.SelectGearScreen
import com.bulkapedia.compose.screens.hero.TopHeroCard
import com.bulkapedia.compose.screens.sets.Set
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.repos.gears.Effect
import com.bulkapedia.compose.data.repos.gears.Gear
import com.bulkapedia.compose.data.repos.gears.GearSet
import com.bulkapedia.compose.data.gears.PersonalGears
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.elements.InRowOutlinedButton
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.util.stringToResource
import com.bulkapedia.compose.util.toHeroStats
import kotlin.math.roundToInt

@Composable
fun CreateSetScreen(
    nickname: String,
    heroId: String,
    setId: String,
    viewModel: CreateSetViewModel
) {
    val heroState = viewModel.heroFlow.collectAsState()
    val set by viewModel.setFlow.collectAsState()
    when (val hero = heroState.value) {
        null -> Loading()
        else -> CreateSetFragment(hero, set.apply { from = nickname }, viewModel)
    }
    DisposableEffect(null) {
        viewModel.fetchData(heroId, setId)
        onDispose { viewModel.removeListeners() }
    }
}

@Composable
fun CreateSetFragment(hero: Hero, set: UserSet, viewModel: CreateSetViewModel) {
    val context = LocalContext.current
    val navController = LocalNavController.current
    // states
    val gears by viewModel.gearsFlow.collectAsState()
    val gearsEffectState = remember(gears) { mutableStateOf(gears) }
    val showEffects = remember { mutableStateOf(false) }
    val effectsTitle = remember { mutableStateOf("Показать характеристики") }
    val selectedGearCell = remember { mutableStateOf(GearCell.HEAD) }
    val showSelectGears = remember { mutableStateOf(false) }

    ScreenView(title = hero.name, showBack = true) {
        if (!showSelectGears.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hero card
                TopHeroCard(hero.icon) {
                    CenteredBox {
                        InRowOutlinedButton(text = "Сохранить") {
                            viewModel.saveSet(UserSet(
                                set.id, set.from, hero.id,
                                gearsEffectState.value.mapValues { it.value.icon },
                                set.userLikeIds
                            ))
                            navController.navigateUp()
                        }
                    }
                }
                // Set view
                HCenteredBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        .background(PrimaryDark, RoundedCornerShape(20.dp))
                        .border(2.dp, Teal200, RoundedCornerShape(20.dp)),
                ) {
                    Set(
                        PaddingValues(20.dp), gearsEffectState.value.mapValues { it.value.icon },
                        onCellClick = {
                            showSelectGears.value = true
                            selectedGearCell.value = it
                        }
                    )
                }
                // Show effects
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        HCenteredBox(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = effectsTitle.value,
                                color = Teal200,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth().clickable {
                                    effectsTitle.value = if (effectsTitle.value == "Показать характеристики") {
                                        "Скрыть характеристики"
                                    } else {
                                        "Показать характеристики"
                                    }
                                    showEffects.value = showEffects.value.not()
                                }
                            )
                        }
                        AnimatedVisibility(showEffects.value) {
                            HCenteredBox(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                                Text(
                                    text = sumEffects(showEffects.value, context, hero, gearsEffectState.value),
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    modifier = Modifier.fillMaxSize().padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        } else {
            CenteredBox(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
            ) {
                SelectGearScreen(
                    showSelectGears,
                    selectedGearCell, hero,
                    gearsEffectState, hiltViewModel()
                )
            }
        }
    }
    LaunchedEffect(null) {
        viewModel.fetchGears(set)
    }
}

private fun sumEffects(
    show: Boolean, ctx: Context, hero: Hero,
    gearIcons: Map<GearCell, Gear>
): AnnotatedString {
    if (!show) {
        return buildAnnotatedString { append("") }
    }
    return buildAnnotatedString {
        val effects = mutableListOf<Effect>()
        val addition = mutableMapOf<Effect, Int>()
        val percent = mutableMapOf<Effect, Int>()
        val gears = gearIcons.map(Map.Entry<GearCell, Gear>::value)
        gears.forEach { g ->
            if (!emptyIcons.contains(g.icon)) {
                effects.addAll(g.effects)
            }
        }
        val pgCount = gears.filter { it.gearSet == GearSet.PERSONAL.name }.size
        effects.addAll(
            GEARS_LIST.getEffectsFromSets(gears) +
            PersonalGears.getPersonalGears(hero, pgCount)
        )
        // divide effects
        effects.forEach { e ->
            val isPercent = e.percent
            val fill: (MutableMap<Effect, Int>) -> Unit = { map ->
                if (map.map { it.key.description }.contains(e.description)) {
                    map.replace(e, map[e]!! + e.number)
                } else map += e to e.number
            }
            fill(if (isPercent) percent else addition)
        }
        // text
        getStats(hero).forEach { (eid, value) ->
            val eff = addition.mapKeys { stringToResource(it.key.description) }[eid]
            val pEff = percent.mapKeys { stringToResource(it.key.description) }[eid]
            val text = effectDescToStatsName(eid, ctx).plus(": ")

            val intOrDblValue = doubleToIntIfNotDouble(value) ?: value
            var number: String

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(text)
                if (text.length >= 21)
                    append("\n")
            }
            withStyle(style = SpanStyle(color = Color.White, fontStyle = FontStyle.Italic)) {
                if (eid == R.string.health_damage_effect ||
                    eid == R.string.armor_damage_effect
                ) {
                    append("X")
                }
                append(intOrDblValue.toString())
            }
            if (eff != null || pEff != null) {
                withStyle(style = SpanStyle(color = Color.Green, fontStyle = FontStyle.Italic)) {
                    var val1 = intOrDblValue
                    var val2 = 0.0

                    if (eff != null && pEff != null) {
                        val1 = sumValueEffects(
                            eid, intOrDblValue,
                            eff, false
                        ).toDouble()
                        val2 = pEff.toDouble()
                    } else if (eff != null) {
                        val2 = eff.toDouble()
                    } else if (pEff != null) {
                        val2 = pEff.toDouble()
                    }
                    val numberDouble = sumValueEffects(eid, val1, val2, pEff != null).toDouble()
                    number = (doubleToIntIfNotDouble(numberDouble) ?: round(numberDouble)).toString()

                    append(" -> ")
                    append(number)
                    append(" (")
                    if (eff != null) {
                        if (eff > 0) {
                            append("+")
                        }
                        append(eff.toString())
                    }
                    if (pEff != null) {
                        if (eff != null) {
                            append(") (")
                        }
                        if (pEff > 0) {
                            append("+")
                        }
                        append(pEff.toString())
                        append("%")
                    }
                    append(")\n")
                }
            } else {
                append("\n")
            }
        }
    }
}

private fun getStats(hero: Hero): MutableMap<Int, Double> {
    val stats = hero.stats.toHeroStats()
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

private fun effectDescToStatsName(effectDesc: Int, context: Context): String {
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
    return if (resId != effectDesc) context.getString(resId)
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
    return if (db - round.toDouble() != 0.0) null
    else round
}

private fun round(num: Double): Double {
    return (num * 100.0).roundToInt() / 100.0
}
