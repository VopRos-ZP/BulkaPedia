package com.bulkapedia.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bulkapedia.ICON_LIST
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.data.gears.Effect
import com.bulkapedia.data.labels.Ranks
import com.bulkapedia.preference.UserPreferences

val HEROES_RES = mapOf(
    "arnie" to R.drawable.arnie_icon,
    "sparkle" to R.drawable.sparkle_icon,
    "cyclops" to R.drawable.cyclops_icon,
    "hurricane" to R.drawable.hurricane_icon,

    "ghost" to R.drawable.ghost_icon,
    "freddie" to R.drawable.freddie_icon,
    "angel" to R.drawable.angel_icon,
    "raven" to R.drawable.raven_icon,

    "blot" to R.drawable.blot_icon,
    "firefly" to R.drawable.firefly_icon,
    "slayer" to R.drawable.slayer_icon,
    "lynx" to R.drawable.lynx_icon,
    "mirage" to R.drawable.mirage_icon,

    "smog" to R.drawable.smog_icon,
    "dragoon" to R.drawable.dragoon_icon,
    "bastion" to R.drawable.bastion_icon,
    "bertha" to R.drawable.bertha_icon,
    "leviathan" to R.drawable.leviathan_icon,

    "stalker" to R.drawable.stalker_icon,
    "doc" to R.drawable.doc_icon,
    "levi" to R.drawable.levi_icon,
    "satoshi" to R.drawable.satoshi_icon,
    // "tess" to R.drawable.arnie_icon // new hero
)

var GEARS_RES = mapOf(
    // common gears
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
    "lock_amulet" to R.drawable.lock_amulet, // lynx_visor
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
    "bio_node_ring" to R.drawable.bio_node_ring, // eye_part
    "bio_node_rad" to R.drawable.bio_node_rad,
    // arnie personal
    "arnie_cap" to R.drawable.arnie_cap,
    "arnie_bandolier" to R.drawable.arnie_bandolier,
    "arnie_bandage" to R.drawable.arnie_bandage,
    "arnie_boot" to R.drawable.arnie_boot, // regular_shoulder_pad
    "arnie_poncho" to R.drawable.arnie_poncho, // runners_boots
    "arnie_rotor" to R.drawable.arnie_rotor,
    // cyclops
    "cyclops_eye" to R.drawable.cyclops_eye,
    "cyclops_heart" to R.drawable.cyclops_heart,
    "cyclops_gloves" to R.drawable.cyclops_gloves,
    "cyclops_boot" to R.drawable.cyclops_boot, // eye_heavy_port
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
    "hurricane_eye" to R.drawable.hurricane_eye, // raven_gloves
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
    "angel_sphere" to R.drawable.angel_sphere, // vest
    // raven
    "raven_mask" to R.drawable.raven_mask,
    "raven_heart" to R.drawable.raven_heart,
    "raven_gloves" to R.drawable.raven_gloves,
    "raven_boot" to R.drawable.raven_boot,
    "raven_necklace" to R.drawable.raven_necklace,
    "raven_radar" to R.drawable.raven_radar,
    // blot
    "blot_brainpan" to R.drawable.blot_brainpan, // heart_part
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
    "lynx_visor" to R.drawable.lynx_visor, //
    "lynx_quiver" to R.drawable.lynx_quiver, //
    "lynx_gloves" to R.drawable.lynx_gloves, // lynx_knee_pads
    "lynx_knee_pads" to R.drawable.lynx_knee_pads, // arrowhead
    "lynx_arrowhead" to R.drawable.lynx_arrowhead, // quiver
    "lynx_earbuds" to R.drawable.lynx_earbuds, // lynx_gloves
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
    "bastion_eye" to R.drawable.bastion_eye, // teck_knee_pads
    "bastion_heart" to R.drawable.bastion_heart, // knuckle common
    "bastion_gloves" to R.drawable.bastion_gloves,
    "bastion_boot" to R.drawable.bastion_boot,
    "bastion_ring" to R.drawable.bastion_ring,
    "bastion_sphere" to R.drawable.bastion_sphere,
    // bertha
    "bertha_eye" to R.drawable.bertha_eye,
    "bertha_belt" to R.drawable.bertha_belt,
    "bertha_sleeve" to R.drawable.bertha_sleeve,
    "bertha_boot" to R.drawable.bertha_boot, // gas grenade
    "bertha_ring" to R.drawable.bertha_ring,
    "bertha_scan" to R.drawable.bertha_scan, // scan
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
//    "" to R.drawable.tess,
//    "" to R.drawable.tess,
//    "" to R.drawable.tess,
//    "" to R.drawable.tess,
//    "" to R.drawable.tess,
//    "" to R.drawable.tess,
    // satoshi
    "satoshi_eye" to R.drawable.satoshi_eye,
    "satoshi_should" to R.drawable.satoshi_should,
    "satoshi_hands" to R.drawable.satoshi_hands,
    "satoshi_leg" to R.drawable.satoshi_leg,
    "satoshi_ring" to R.drawable.satoshi_ring,
    "satoshi_sphere" to R.drawable.satoshi_sphere,
)

fun addUserToShared(shared: SharedPreferences, prefs: UserPreferences) {
    shared.edit {
        putBoolean(UserPreferences.SIGNED, prefs.getSigned())
        putString(UserPreferences.EMAIL, prefs.getEmail())
        putString(UserPreferences.PASSWORD, prefs.getPassword())
        putString(UserPreferences.NICKNAME, prefs.getNickname())
        apply()
    }
}

fun updateBoolShared() {
    MAIN.getPreferences().edit {
        putBoolean(UserPreferences.SIGNED, MAIN.prefs.getSigned())
        putBoolean(UserPreferences.NEWS, MAIN.prefs.getNews())
        apply()
    }
}

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
