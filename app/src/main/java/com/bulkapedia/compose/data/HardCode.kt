package com.bulkapedia.compose.data

object HardCode {

    object Hero {
        val values = listOf(
            "arnie_icon", "cyclops_icon", "sparkle_icon", "hurricane_icon",
            "ghost_icon", "freddie_icon", "angel_icon", "raven_icon",
            "blot_icon", "firefly_icon", "slayer_icon", "mirage_icon", "lynx_icon",
            "smog_icon", "dragoon_icon", "bastion_icon", "bertha_icon", "leviathan_icon",
            "stalker_icon", "doc_icon", "levi_icon", "satoshi_icon", "tess_icon"
        )

        fun getType(icon: String): String {
            return when (icon) {
                in listOf("arnie_icon", "cyclops_icon", "sparkle_icon", "hurricane_icon") -> "shortguns"
                in listOf("ghost_icon", "freddie_icon", "angel_icon", "raven_icon") -> "scouts"
                in listOf("blot_icon", "firefly_icon", "slayer_icon", "mirage_icon", "lynx_icon") -> "snipers"
                in listOf("smog_icon", "dragoon_icon", "bastion_icon", "bertha_icon", "leviathan_icon") -> "tanks"
                in listOf("stalker_icon", "doc_icon", "levi_icon", "satoshi_icon", "tess_icon") -> "troopers"
                else -> ""
            }
        }
    }

    object Icon {

        fun headIcons(): List<String> {
            return listOf(
                "empty_head",
                "combat_headband", "commanders_beret", "protective_glasses",
                "special_forces_optics", "tactical_optics",
                "white_index_eye", "eye_part", "dark_eye",
                "eye_heavy_port", "bio_node_eye",
            )
        }
        fun bodyIcons(): List<String> {
            return listOf(
                "empty_body",
                "vest", "body_armor_implant", "reflex_implant",
                "healing_implant", "regular_body_armor",
                "white_index_heart", "heart_part", "dark_heart",
                "heart_heavy_port", "bio_node_heart",
            )
        }
        fun armIcons(): List<String> {
            return listOf(
                "empty_arm",
                "spiked_shoulder", "tactical_gloves", "regular_shoulder_pad",
                "special_forces_gloves", "technical_shoulder",
                "white_index_hand", "arm_part", "dark_arm",
                "arm_heavy_port", "bio_node_arm",
            )
        }
        fun legIcons(): List<String> {
            return listOf(
                "empty_leg",
                "runners_boots", "precision_implant", "teck_knee_pads",
                "regular_boot", "tactical_knee_pads",
                "white_index_leg", "leg_part", "dark_boot",
                "leg_heavy_port", "bio_node_leg",
            )
        }
        fun decorIcons(): List<String> {
            return listOf(
                "empty_decor",
                "knuckle", "energy_coils", "lock_amulet",
                "wedding_ring", "personal_id_ring",
                "white_index_collar", "ring_part", "dark_ring",
                "ring_heavy_port", "bio_node_ring",
            )
        }
        fun deviceIcons(): List<String> {
            return listOf(
                "empty_device",
                "gas_grenade", "sphere", "exploder",
                "scan", "echo_radar",
                "white_index_scan", "pad_part", "dark_rad",
                "scan_heavy_port", "bio_node_rad",
            )
        }

    }

}