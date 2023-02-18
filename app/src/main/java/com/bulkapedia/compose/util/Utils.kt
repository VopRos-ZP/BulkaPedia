package com.bulkapedia.compose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.bulkapedia.compose.ICON_LIST
import com.bulkapedia.R
import com.bulkapedia.compose.MainActivity
import com.bulkapedia.compose.data.gears.Effect
import com.bulkapedia.compose.data.labels.Ranks

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
    // maps
    "hotel" to R.drawable.hotel,
    "railway_station" to R.drawable.railway_station,
    "casino" to R.drawable.casino,
    "villa" to R.drawable.villa,
    "road_motel" to R.drawable.road_motel,
    "village" to R.drawable.village,
    "city" to R.drawable.city,
    "city_garden" to R.drawable.city_garden,
    "police_station" to R.drawable.police_station,
    "sawmill" to R.drawable.sawmill,
    "fortress" to R.drawable.fortress,
    // ЦГ
    "hospital" to R.drawable.hospital,
    "circus" to R.drawable.circus,
    "psychiatric_hospital" to R.drawable.psychiatric_hospital,
    "forgotten_bazar" to R.drawable.forgotten_bazar,
    // Стенка
    "sewerage" to R.drawable.sewerage,
    "dungeon" to R.drawable.dungeon,
    "amusement_park" to R.drawable.amusement_park,
    "factory" to R.drawable.factory,
    "mysterious_island" to R.drawable.mysterious_island,
    // Саботаж
    "underground_base" to R.drawable.underground_base,
    "military_storage" to R.drawable.military_storage,
    "secret_floor" to R.drawable.secret_floor,
    "boarding" to R.drawable.boarding,
    // spawns
    "hotel_spawns" to R.drawable.hotel_spawns,
    "railway_station_spawns" to R.drawable.railway_station_spawns,
    "casino_spawns" to R.drawable.casino_spawns,
    "villa_spawns" to R.drawable.villa_spawns,
    "road_motel_spawns" to R.drawable.road_motel_spawns,
    "village_spawns" to R.drawable.village_spawns,
    "city_spawns" to R.drawable.city_spawns,
    "city_garden_spawns" to R.drawable.city_garden_spawns,
    "police_station_spawns" to R.drawable.police_station_spawns,
    "sawmill_spawns" to R.drawable.sawmill_spawns,
    "fortress_spawns" to R.drawable.fortress_spawns,
    // ЦГ
    "hospital_spawns" to R.drawable.hospital_spawns,
    "circus_spawns" to R.drawable.circus_spawns,
    "psychiatric_hospital_spawns" to R.drawable.psychiatric_hospital_spawns,
    "forgotten_bazar_spawns" to R.drawable.forgotten_bazar_spawns,
    // Стенка
    "sewerage_spawns" to R.drawable.sewerage_spawns,
    "dungeon_spawns" to R.drawable.dungeon_spawns,
    "amusement_park_spawns" to R.drawable.amusement_park_spawns,
    "factory_spawns" to R.drawable.factory_spawns,
    "mysterious_island_spawns" to R.drawable.mysterious_island_spawns,
    // Саботаж
    "underground_base_spawns" to R.drawable.underground_base_spawns,
    "military_storage_spawns" to R.drawable.military_storage_spawns,
    "secret_floor_spawns" to R.drawable.secret_floor_spawns,
    "boarding_spawns" to R.drawable.boarding_spawns,
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
    // common gears
    // empty
    "empty_head" to R.drawable.empty_head,
    "empty_body" to R.drawable.empty_body,
    "empty_arm" to R.drawable.empty_arm,
    "empty_leg" to R.drawable.empty_leg,
    "empty_decor" to R.drawable.empty_decor,
    "empty_device" to R.drawable.empty_device,
    // head
    "tactical_optics" to R.drawable.tactical_optics,
    "combat_headband" to R.drawable.combat_headband,
    "commanders_beret" to R.drawable.commanders_beret,
    "protective_glasses" to R.drawable.protective_glasses,
    "special_forces_optics" to R.drawable.special_forces_optics,
    // body
    "reflex_implant" to R.drawable.reflex_implant,
    "vest" to R.drawable.vest,
    "regular_body_armor" to R.drawable.regular_body_armor,
    "body_armor_implant" to R.drawable.body_armor_implant,
    "healing_implant" to R.drawable.healing_implant,
    // arm
    "spiked_shoulder" to R.drawable.spiked_shoulder,
    "tactical_gloves" to R.drawable.tactical_gloves,
    "regular_shoulder_pad" to R.drawable.regular_shoulder_pad,
    "special_forces_gloves" to R.drawable.special_forces_gloves,
    "technical_shoulder" to R.drawable.technical_shoulder,
    // leg
    "runners_boots" to R.drawable.runners_boots,
    "regular_boot" to R.drawable.regular_boot,
    "precision_implant" to R.drawable.precision_implant,
    "teck_knee_pads" to R.drawable.teck_knee_pads,
    "tactical_knee_pads" to R.drawable.tactical_knee_pads,
    // decor
    "knuckle" to R.drawable.knuckle,
    "lock_amulet" to R.drawable.lock_amulet,
    "wedding_ring" to R.drawable.wedding_ring,
    "energy_coils" to R.drawable.energy_coils,
    "personal_id_ring" to R.drawable.personal_id_ring,
    // device
    "gas_grenade" to R.drawable.gas_grenade,
    "exploder" to R.drawable.exploder,
    "scan" to R.drawable.scan,
    "echo_radar" to R.drawable.echo_radar,
    "sphere" to R.drawable.sphere,
    // white index
    "white_index_eye" to R.drawable.white_index_eye,
    "white_index_heart" to R.drawable.white_index_heart,
    "white_index_hand" to R.drawable.white_index_hand,
    "white_index_leg" to R.drawable.white_index_leg,
    "white_index_collar" to R.drawable.white_index_collar,
    "white_index_scan" to R.drawable.white_index_scan,
    // part
    "eye_part" to R.drawable.eye_part,
    "heart_part" to R.drawable.heart_part,
    "arm_part" to R.drawable.arm_part,
    "leg_part" to R.drawable.leg_part,
    "ring_part" to R.drawable.ring_part,
    "pad_part" to R.drawable.pad_part,
    // dark implant
    "dark_eye" to R.drawable.dark_eye,
    "dark_heart" to R.drawable.dark_heart,
    "dark_arm" to R.drawable.dark_arm,
    "dark_boot" to R.drawable.dark_boot,
    "dark_ring" to R.drawable.dark_ring,
    "dark_rad" to R.drawable.dark_rad,
    // heavy port
    "eye_heavy_port" to R.drawable.eye_heavy_port,
    "heart_heavy_port" to R.drawable.heart_heavy_port,
    "arm_heavy_port" to R.drawable.arm_heavy_port,
    "leg_heavy_port" to R.drawable.leg_heavy_port,
    "ring_heavy_port" to R.drawable.ring_heavy_port,
    "scan_heavy_port" to R.drawable.scan_heavy_port,
    // bio node
    "bio_node_eye" to R.drawable.bio_node_eye,
    "bio_node_heart" to R.drawable.bio_node_heart,
    "bio_node_arm" to R.drawable.bio_node_arm,
    "bio_node_leg" to R.drawable.bio_node_leg,
    "bio_node_ring" to R.drawable.bio_node_ring,
    "bio_node_rad" to R.drawable.bio_node_rad,
    // arnie personal
    "arnie_cap" to R.drawable.arnie_cap,
    "arnie_bandolier" to R.drawable.arnie_bandolier,
    "arnie_bandage" to R.drawable.arnie_bandage,
    "arnie_boot" to R.drawable.arnie_boot,
    "arnie_poncho" to R.drawable.arnie_poncho,
    "arnie_rotor" to R.drawable.arnie_rotor,
    // cyclops
    "cyclops_eye" to R.drawable.cyclops_eye,
    "cyclops_heart" to R.drawable.cyclops_heart,
    "cyclops_gloves" to R.drawable.cyclops_gloves,
    "cyclops_boot" to R.drawable.cyclops_boot,
    "cyclops_coin" to R.drawable.cyclops_coin,
    "cyclops_radar" to R.drawable.cyclops_radar,
    // sparkle
    "sparkle_eye" to R.drawable.sparkle_eye,
    "sparkle_belt" to R.drawable.sparkle_belt,
    "sparkle_gloves" to R.drawable.sparkle_gloves,
    "sparkle_boot" to R.drawable.sparkle_boot,
    "sparkle_chocker" to R.drawable.sparkle_chocker,
    "sparkle_grenade" to R.drawable.sparkle_grenade,
    // hurricane
    "hurricane_eye" to R.drawable.hurricane_eye,
    "hurricane_belt" to R.drawable.hurricane_belt,
    "hurricane_gloves" to R.drawable.hurricane_gloves,
    "hurricane_boot" to R.drawable.hurricane_boot,
    "hurricane_ring" to R.drawable.hurricane_ring,
    "hurricane_crystal" to R.drawable.hurricane_crystal,
    // ghost
    "ghost_eye" to R.drawable.ghost_eye,
    "ghost_heart" to R.drawable.ghost_heart,
    "ghost_knuckle" to R.drawable.ghost_knuckle,
    "ghost_boot" to R.drawable.ghost_boot,
    "ghost_necklace" to R.drawable.ghost_necklace,
    "ghost_dynamo" to R.drawable.ghost_dynamo,
    // freddie
    "freddie_mask" to R.drawable.freddie_mask,
    "freddie_bandolier" to R.drawable.freddie_bandolier,
    "freddie_knuckle" to R.drawable.freddie_knuckle,
    "freddie_boot" to R.drawable.freddie_boot,
    "freddie_ring" to R.drawable.freddie_ring,
    "freddie_grenade" to R.drawable.freddie_grenade,
    // angel
    "angel_eye" to R.drawable.angel_eye,
    "angel_heart" to R.drawable.angel_heart,
    "angel_gloves" to R.drawable.angel_gloves,
    "angel_leg" to R.drawable.angel_leg,
    "angel_ring" to R.drawable.angel_ring,
    "angel_sphere" to R.drawable.angel_sphere,
    // raven
    "raven_mask" to R.drawable.raven_mask,
    "raven_heart" to R.drawable.raven_heart,
    "raven_gloves" to R.drawable.raven_gloves,
    "raven_boot" to R.drawable.raven_boot,
    "raven_necklace" to R.drawable.raven_necklace,
    "raven_radar" to R.drawable.raven_radar,
    // blot
    "blot_brainpan" to R.drawable.blot_brainpan,
    "blot_heart" to R.drawable.blot_heart,
    "blot_shoulder" to R.drawable.blot_shoulder,
    "blot_leg" to R.drawable.blot_leg,
    "blot_ring" to R.drawable.blot_ring,
    "blot_device" to R.drawable.blot_device,
    // firefly
    "firefly_glasses" to R.drawable.firefly_glasses,
    "firefly_heart" to R.drawable.firefly_heart,
    "firefly_knuckle" to R.drawable.firefly_knuckle,
    "firefly_boot" to R.drawable.firefly_boot,
    "firefly_necklace" to R.drawable.firefly_necklace,
    "firefly_grenade" to R.drawable.firefly_grenade,
    // lynx
    "lynx_visor" to R.drawable.lynx_visor,
    "lynx_quiver" to R.drawable.lynx_quiver,
    "lynx_gloves" to R.drawable.lynx_gloves,
    "lynx_knee_pads" to R.drawable.lynx_knee_pads,
    "lynx_arrowhead" to R.drawable.lynx_arrowhead,
    "lynx_earbuds" to R.drawable.lynx_earbuds,
    // slayer
    "slayer_headphones" to R.drawable.slayer_headphones,
    "slayer_pouch" to R.drawable.slayer_pouch,
    "slayer_bandage" to R.drawable.slayer_bandage,
    "slayer_boot" to R.drawable.slayer_boot,
    "slayer_tags" to R.drawable.slayer_tags,
    "slayer_rad" to R.drawable.slayer_rad,
    // mirage
    "mirage_eye" to R.drawable.mirage_eye,
    "mirage_belt" to R.drawable.mirage_belt,
    "mirage_knuckle" to R.drawable.mirage_knuckle,
    "mirage_boot" to R.drawable.mirage_boot,
    "mirage_choker" to R.drawable.mirage_choker,
    "mirage_antigrav" to R.drawable.mirage_antigrav,
    // smog
    "smog_mask" to R.drawable.smog_mask,
    "smog_heart" to R.drawable.smog_heart,
    "smog_gloves" to R.drawable.smog_gloves,
    "smog_boot" to R.drawable.smog_boot,
    "smog_tag" to R.drawable.smog_tag,
    "smog_rocket" to R.drawable.smog_rocket,
    // dragoon
    "dragoon_hat" to R.drawable.dragoon_hat,
    "dragoon_heart" to R.drawable.dragoon_heart,
    "dragoon_shoulder_pad" to R.drawable.dragoon_shoulder_pad,
    "dragoon_boot" to R.drawable.dragoon_boot,
    "dragoon_chain" to R.drawable.dragoon_chain,
    "dragoon_rotor" to R.drawable.dragoon_rotor,
    // bastion
    "bastion_eye" to R.drawable.bastion_eye,
    "bastion_heart" to R.drawable.bastion_heart,
    "bastion_gloves" to R.drawable.bastion_gloves,
    "bastion_boot" to R.drawable.bastion_boot,
    "bastion_ring" to R.drawable.bastion_ring,
    "bastion_sphere" to R.drawable.bastion_sphere,
    // bertha
    "bertha_eye" to R.drawable.bertha_eye,
    "bertha_belt" to R.drawable.bertha_belt,
    "bertha_sleeve" to R.drawable.bertha_sleeve,
    "bertha_boot" to R.drawable.bertha_boot,
    "bertha_ring" to R.drawable.bertha_ring,
    "bertha_scan" to R.drawable.bertha_scan,
    // leviathan
    "leviathan_cap" to R.drawable.leviathan_cap,
    "leviathan_implant" to R.drawable.leviathan_implant,
    "leviathan_shoulder" to R.drawable.leviathan_shoulder,
    "leviathan_boot" to R.drawable.leviathan_boot,
    "leviathan_ring" to R.drawable.leviathan_ring,
    "leviathan_exploder" to R.drawable.leviathan_exploder,
    // stalker
    "stalker_hat" to R.drawable.stalker_hat,
    "stalker_belt" to R.drawable.stalker_belt,
    "stalker_shoulder_pad" to R.drawable.stalker_shoulder_pad,
    "stalker_boot" to R.drawable.stalker_boot,
    "stalker_chain" to R.drawable.stalker_chain,
    "stalker_razor" to R.drawable.stalker_razor,
    // doc
    "doc_mask" to R.drawable.doc_mask,
    "doc_belt" to R.drawable.doc_belt,
    "doc_gloves" to R.drawable.doc_gloves,
    "doc_boot" to R.drawable.doc_boot,
    "doc_necklace" to R.drawable.doc_necklace,
    "doc_rocket" to R.drawable.doc_rocket,
    // levi
    "levi_patch" to R.drawable.levi_patch,
    "levi_belt" to R.drawable.levi_belt,
    "levi_whip" to R.drawable.levi_whip,
    "levi_boot" to R.drawable.levi_boot,
    "levi_tag" to R.drawable.levi_tag,
    "levi_radio" to R.drawable.levi_radio,
    // tess
//    "tess_horn" to R.drawable.tess_horn,
//    "tess_heart" to R.drawable.tess_heart,
//    "tess_gloves" to R.drawable.tess_gloves,
//    "tess_boot" to R.drawable.tess_boot,
//    "tess_ring" to R.drawable.tess_ring,
//    "tess_tazer" to R.drawable.tess_tazer,
    // satoshi
    "satoshi_eye" to R.drawable.satoshi_eye,
    "satoshi_should" to R.drawable.satoshi_should,
    "satoshi_hands" to R.drawable.satoshi_hands,
    "satoshi_leg" to R.drawable.satoshi_leg,
    "satoshi_ring" to R.drawable.satoshi_ring,
    "satoshi_sphere" to R.drawable.satoshi_sphere,

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
    // "tess" to R.string.tess // new hero
    // maps names
    "hotel_txt" to R.string.hotel,
    "railway_station_txt" to R.string.railway_station,
    "casino_txt" to R.string.casino,
    "villa_txt" to R.string.villa,
    "road_motel_txt" to R.string.road_motel,
    "village_txt" to R.string.village,
    "city_txt" to R.string.city,
    "city_garden_txt" to R.string.city_garden,
    "police_station_txt" to R.string.police_station,
    "sawmill_txt" to R.string.sawmill,
    "fortress_txt" to R.string.fortress,
    // ЦГ
    "hospital_txt" to R.string.hospital,
    "circus_txt" to R.string.circus,
    "psychiatric_hospital_txt" to R.string.psychiatric_hospital,
    "forgotten_bazar_txt" to R.string.forgotten_bazar,
    // Стенка
    "sewerage_txt" to R.string.sewerage,
    "dungeon_txt" to R.string.dungeon,
    "amusement_park_txt" to R.string.amusement_park,
    "factory_txt" to R.string.factory,
    "mysterious_island_txt" to R.string.mysterious_island,
    // Саботаж
    "underground_base_txt" to R.string.underground_base,
    "military_storage_txt" to R.string.military_storage,
    "secret_floor_txt" to R.string.secret_floor,
    "boarding_txt" to R.string.boarding,
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

fun autoFillGearEffects(effects: List<Effect>, ints: Map<Int, List<Int>>): Map<Ranks, List<Effect>> {
    val m = mutableMapOf<Ranks, List<Effect>>()
    Ranks.values().forEachIndexed { i, r ->
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
