package com.bulkapedia.compose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.bulkapedia.compose.ICON_LIST
import com.bulkapedia.R
import com.bulkapedia.compose.MainActivity
import bulkapedia.effects.Effect
import bulkapedia.gears.Rank

lateinit var CTX: MainActivity

fun Modifier.clickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick)
}

val HEROES_RES = mapOf(
    "arnie_icon" to R.drawable.arnie_icon,
    "sparkle_icon" to R.drawable.sparkle_icon,
    "cyclops_icon" to R.drawable.cyclops_icon,
    "hurricane_icon" to R.drawable.hurricane_icon,

    "ghost_icon" to R.drawable.ghost_icon,
    "freddie_icon" to R.drawable.freddie_icon,
    "angel_icon" to R.drawable.angel_icon,
    "raven_icon" to R.drawable.raven_icon,

    "blot_icon" to R.drawable.blot_icon,
    "firefly_icon" to R.drawable.firefly_icon,
    "slayer_icon" to R.drawable.slayer_icon,
    "lynx_icon" to R.drawable.lynx_icon,
    "mirage_icon" to R.drawable.mirage_icon,

    "smog_icon" to R.drawable.smog_icon,
    "dragoon_icon" to R.drawable.dragoon_icon,
    "bastion_icon" to R.drawable.bastion_icon,
    "bertha_icon" to R.drawable.bertha_icon,
    "leviathan_icon" to R.drawable.leviathan_icon,

    "stalker_icon" to R.drawable.stalker_icon,
    "doc_icon" to R.drawable.doc_icon,
    "levi_icon" to R.drawable.levi_icon,
    "satoshi_icon" to R.drawable.satoshi_icon,
    "tess_icon" to R.drawable.tess_icon,
    // map modes
    "battle_royale" to R.string.battle_royale_mode,
    "king_of_hill" to R.string.kings_of_hill_mode,
    "squad" to R.string.squad_mode,
    "sabotage" to R.string.sabotage_mode,
)

var GEARS_RES = mapOf(
    "tanks" to R.string.tanks,
    "scouts" to R.string.scouts,
    "snipers" to R.string.snipers,
    "troopers" to R.string.troopers,
    "shortguns" to R.string.shortguns,

    "info" to R.drawable.info,
    "maps" to R.drawable.maps,
    "mechanics" to R.drawable.mechanics,
    // strings
    "arnie" to R.string.arnie,
    "sparkle" to R.string.sparkle,
    "cyclops" to R.string.cyclops,
    "hurricane" to R.string.hurricane,

    "ghost" to R.string.ghost,
    "freddie" to R.string.freddie,
    "angel" to R.string.angel,
    "raven" to R.string.raven,

    "blot" to R.string.blot,
    "firefly" to R.string.firefly,
    "slayer" to R.string.slayer,
    "lynx" to R.string.lynx,
    "mirage" to R.string.mirage,

    "smog" to R.string.smog,
    "dragoon" to R.string.dragoon,
    "bastion" to R.string.bastion,
    "bertha" to R.string.bertha,
    "leviathan" to R.string.leviathan,

    "stalker" to R.string.stalker,
    "doc" to R.string.doc,
    "levi" to R.string.levi,
    "satoshi" to R.string.satoshi,
    "tess" to R.string.tess,
    // effects
    "armor_damage_effect" to R.string.armor_damage_effect,
    "damage_effect" to R.string.damage_effect,
    "health_damage_effect" to R.string.health_damage_effect,
    "max_health_effect" to R.string.max_health_effect,
    "max_armor_effect" to R.string.max_armor_effect,
    "add_health_effect" to R.string.add_health_effect,
    "add_armor_effect" to R.string.add_armor_effect,
    "speed_effect" to R.string.speed_effect,
    "speed_in_focus_effect" to R.string.speed_in_focus_effect,
    "spread_in_move_effect" to R.string.spread_in_move_effect,
    "spread_in_focus_effect" to R.string.spread_in_focus_effect,
    "spread_in_not_focus_effect" to R.string.spread_in_not_focus_effect,
    "spread_recoil_effect" to R.string.spread_recoil_effect,
    "visibility_effect" to R.string.visibility_effect,
    "piercing_effect" to R.string.piercing_effect,
    "fire_rate_effect" to R.string.fire_rate_effect,
    "fire_range_focus_effect" to R.string.fire_range_focus_effect,
    "fire_range_effect" to R.string.fire_range_effect,
    "aiming_speed_effect" to R.string.aiming_speed_effect,
    "running_volume_effect" to R.string.running_volume_effect,
    "time_up_ammo_effect" to R.string.time_up_ammo_effect,
    "time_up_bust_effect" to R.string.time_up_bust_effect,
    "reloading_time_effect" to R.string.reloading_time_effect,
    "piercing_power_effect" to R.string.piercing_power_effect,
    "add_patrons_effect" to R.string.add_patrons_effect,
    "no_effects" to R.string.no_effects,

    "damage" to R.string.damage,
    "vision" to R.string.vision,
    "maxHealth" to R.string.health,
    "maxArmor" to R.string.armor,
    "maxSilence" to R.string.max_silence,
    "maxSpeed" to R.string.max_speed,
    "maxSpeedInFocus" to R.string.max_speed_in_focus,
    "reloadingTime" to R.string.reloading_time,
    "fireRate" to R.string.fire_rate,
    "fireRange" to R.string.fire_range,
    "rangeInFocus" to R.string.fire_range_in_focus,
    "spreadOnStay" to R.string.fire_spread,
    "spreadInMove" to R.string.fire_spread_while_moving,
    "spreadInFocus" to R.string.fire_spread_in_focus,
    "patrons" to R.string.magazine_size,
    "powerPenetration" to R.string.piercing_power,
    "damageOnHealth" to R.string.health_damage_mod,
    "damageOnArmor" to R.string.armor_damage_mod,
    "armorPenetration" to R.string.armor_penetration,
    "aimingTime" to R.string.aiming_time,
)

fun autoFillGearEffects(effects: List<Effect>, ints: Map<Int, List<Int>>): Map<Rank, List<Effect>> {
    val m = mutableMapOf<Rank, List<Effect>>()
    Rank.values().forEachIndexed { i, r ->
        m += r to effects.mapIndexed { ei, it ->
            Effect(ints[ei]!![i], it.percent, it.description)
        }
    }
    return m
}

fun stringToResource(str: String): Int = try { ICON_LIST[str]!! } catch (_: Exception) { 0 }

fun resourceToString(res: Int): String {
    return ICON_LIST.map { it.value to it.key }.toMap()[res] ?: ""
}
