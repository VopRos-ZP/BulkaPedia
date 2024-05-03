package ru.bulkapedia.domain.utils

import android.util.Log
import ru.bulkapedia.R

class ResourceManager {

    private val strings = mapOf(
        "arnie" to R.string.arnie,
        "sparkle" to R.string.sparkle,
        "cyclops" to R.string.cyclops,
        "hurricane" to R.string.hurricane,
        "shenji" to R.string.shenji,

        "ghost" to R.string.ghost,
        "freddie" to R.string.freddie,
        "angel" to R.string.angel,
        "raven" to R.string.raven,
        "alice" to R.string.alice,

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

        // map modes
        "battle_royale" to R.string.battle_royale_mode,
        "king_of_the_hill" to R.string.kings_of_hill_mode,
        "squad" to R.string.squad_mode,
        "sabotage" to R.string.sabotage_mode,
        /* Map names */
        "amusement_park" to R.string.amusement_park,
        "bank" to R.string.bank,
        "casino" to R.string.casino,
        "circus" to R.string.circus,
        "city" to R.string.city,
        "city_garden" to R.string.city_garden,
        "dungeon" to R.string.dungeon,
        "factory" to R.string.factory,
        "forgotten_bazar" to R.string.forgotten_bazar,
        "hospital" to R.string.hospital,
        "hotel" to R.string.hotel,
        "military_storage" to R.string.military_storage,
        "neo_town" to R.string.neo_town,
        "police_station" to R.string.police_station,
        "psychiatric_hospital" to R.string.psychiatric_hospital,
        "railway_station" to R.string.railway_station,
        "road_motel" to R.string.road_motel,
        "sawmill" to R.string.sawmill,
        "secret_floor" to R.string.secret_floor,
        "sewerage" to R.string.sewerage,
        "space_prison" to R.string.space_prison,
        "underground_base" to R.string.underground_base,
        "villa" to R.string.villa,
        "village" to R.string.village,
        "wreckage_point" to R.string.wrackage_point,

        "tanks" to R.string.tanks,
        "scouts" to R.string.scouts,
        "snipers" to R.string.snipers,
        "troopers" to R.string.troopers,
        "shortguns" to R.string.shortguns,
    )

    fun fromSource(id: Int): String {
        return try {
            strings.entries.associate { (k, v) -> v to k }[id] ?: ""
        } catch (_: Exception) { "" }
    }

    fun toSource(str: String?): Int {
        return try {
            strings[str]!!
        } catch (_: Exception) {
            Log.d("ResourceManager", "Resource `$str` not found")
            R.string.empty
        }
    }

}