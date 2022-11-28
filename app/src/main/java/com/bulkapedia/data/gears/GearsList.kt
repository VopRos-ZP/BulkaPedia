package com.bulkapedia.data.gears

import com.bulkapedia.R
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.heroes.HeroList
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.autoFillGearEffects
import java.util.*

class GearsList {

    companion object {
        /** Вещи на голову **/
        val TACTICAL_OPTICS = Gear(GearSet.NONE, R.drawable.tactical_optics, listOf(
            Effect(-25, true, R.string.spread_in_not_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_not_focus_effect)),
                mapOf(0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25))
            )
        )
        val COMBAT_HEADBAND = Gear(GearSet.NONE, R.drawable.combat_headband, listOf(
            Effect(24, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(24, false, R.string.fire_range_focus_effect)),
                mapOf(0 to listOf(10, 12, 14, 16, 18, 20, 22, 23, 24))
            )
        )
        val COMMANDERS_BERET = Gear(GearSet.NONE, R.drawable.commanders_beret, listOf(
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.running_volume_effect)),
                mapOf(0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25))
            )
        )
        val PROTECTIVE_GLASSES = Gear(GearSet.NONE, R.drawable.protective_glasses, listOf(
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect)),
                mapOf(0 to listOf(30, 35, 40, 45, 50, 55, 60, 63, 65))
            )
        )
        val SPECIAL_FORCES_OPTICS = Gear(GearSet.NONE, R.drawable.special_forces_optics, listOf(
            Effect(-24, true, R.string.aiming_speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-24, true, R.string.aiming_speed_effect)),
                mapOf(0 to listOf(-10, -12, -14, -16, -18, -20, -22, -23, -24))
            )
        )

        val WHITE_INDEX_EYE = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_eye, listOf(
            Effect(23, false, R.string.fire_range_focus_effect),
            Effect(63, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(23, false, R.string.fire_range_focus_effect),
                Effect(63, false, R.string.visibility_effect)),
                mapOf(
                    0 to listOf(10, 12, 14, 16, 18, 20, 22, 22, 23),
                    1 to listOf(30, 35, 40, 45, 50, 55, 60, 63, 63)
                )
            )
        )
        val EYE_PART = Gear(GearSet.PARTS, R.drawable.eye_part, listOf(
            Effect(-36, true, R.string.spread_in_focus_effect),
            Effect(11, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                    Effect(-36, true, R.string.spread_in_focus_effect),
                    Effect(11, true, R.string.speed_effect)
                ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -35, -36),
                    1 to listOf( 2,   4,   6,   7,   8,   9,  10,  11,  11)
                )
            )
        )
        val EYE_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_eye, listOf(
            Effect(62, false, R.string.visibility_effect),
            Effect(10, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(62, false, R.string.visibility_effect),
                Effect(10, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(30, 35, 40, 45, 50, 55, 60, 62, 62),
                    1 to listOf(3, 4, 5, 6, 7, 8, 9, 9, 10)
                )
            )
        )
        val EYE_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.eye_heavy_port, listOf(
            Effect(62, false, R.string.visibility_effect),
            Effect(-24, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(62, false, R.string.visibility_effect),
                Effect(-24, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(30, 35, 40, 45, 50, 55, 60, 60, 62),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -24)
                )
            ))
        val BIO_NODE_EYE = Gear(GearSet.BIO_NODE, R.drawable.bio_node_eye, listOf(
            Effect(-25, true, R.string.spread_in_not_focus_effect),
            Effect(18, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_not_focus_effect),
                Effect(18, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -25, -25),
                    1 to listOf(4, 6, 8, 10, 12, 14, 16, 16, 18)
                )
            ))

        val headIcon = mutableListOf(
            TACTICAL_OPTICS, COMBAT_HEADBAND, COMMANDERS_BERET,
            PROTECTIVE_GLASSES, SPECIAL_FORCES_OPTICS
        )

        /** Вещи на тело **/
        val REFLEX_IMPLANT = Gear(GearSet.NONE, R.drawable.reflex_implant, listOf(
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))
        val INFANTRY_VEST = Gear(GearSet.NONE, R.drawable.vest, listOf(
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 6, 7, 8, 9, 10, 11, 12)
                )
            ))
        val REGULAR_BODY_ARMOR = Gear(GearSet.NONE, R.drawable.regular_body_armor, listOf(
            Effect(12, true, R.string.max_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 6, 7, 8, 9, 10, 11, 12)
                )
            ))
        val HEALING_IMPLANT = Gear(GearSet.NONE, R.drawable.healing_implant, listOf(
            Effect(17, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(17, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(2, 5, 7, 8, 10, 12, 15, 16, 17)
                )
            ))
        val BODY_ARMOR_IMPLANT = Gear(GearSet.NONE, R.drawable.body_armor_implant, listOf(
            Effect(17, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(17, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(2, 5, 7, 8, 10, 12, 15, 16, 17)
                )
            ))

        val bodyIcons = mutableListOf(
            REFLEX_IMPLANT, INFANTRY_VEST, REGULAR_BODY_ARMOR,
            HEALING_IMPLANT, BODY_ARMOR_IMPLANT
        )

        val WHITE_INDEX_HEART = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_heart, listOf(
            Effect(10, true, R.string.armor_damage_effect),
            Effect(12, true, R.string.max_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.armor_damage_effect),
                Effect(12, true, R.string.max_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 4, 5, 6, 7, 8, 9, 9, 10),
                    1 to listOf(3, 5, 6, 7, 8, 9, 10, 12, 12)
                )
            ))
        val HEART_PART = Gear(GearSet.PARTS, R.drawable.heart_part, listOf(
            Effect(16, false, R.string.add_health_effect),
            Effect(9, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(16, false, R.string.add_health_effect),
                Effect(9, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 7, 10, 12, 13, 15, 16, 16),
                    1 to listOf(2, 3, 4, 5, 6, 7, 8, 8, 9)
                )
            ))
        val HEART_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_heart, listOf(
            Effect(16, false, R.string.add_armor_effect),
            Effect(11, true, R.string.max_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(16, false, R.string.add_armor_effect),
                Effect(11, true, R.string.max_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 7, 10, 12, 13, 15, 16, 16),
                    1 to listOf(3, 5, 6, 7, 8, 9, 10, 10, 11)
                )
            ))
        val HEART_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.heart_heavy_port, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 8, 9),
                    1 to listOf(-5, -10, -15, -20, -25, -30, -35, -37, -37)
                )
            ))
        val BIO_NODE_HEART = Gear(GearSet.BIO_NODE, R.drawable.bio_node_heart, listOf(
            Effect(10, false, R.string.piercing_power_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_power_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 9, 10),
                    1 to listOf(2, 3, 4, 5, 6, 7, 8, 9, 9)
                )
            ))

        /** Вещи на руки **/
        val SPIKED_SHOULDER = Gear(GearSet.NONE, R.drawable.spiked_shoulder, listOf(
            Effect(16, true, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(16, true, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(2, 4, 6, 8, 10, 12, 14, 15, 16)
                )
            ))
        val TACTICAL_GLOVES = Gear(GearSet.NONE, R.drawable.tactical_gloves, listOf(
            Effect(-37, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))
        val REGULAR_SHOULDER_PAD = Gear(GearSet.NONE, R.drawable.regular_shoulder_pad, listOf(
            Effect(12, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(2, 4, 6, 7, 8, 9, 10, 11, 12)
                )
            ))
        val SPECIAL_FORCES_GLOVES = Gear(GearSet.NONE, R.drawable.special_forces_gloves, listOf(
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))
        val TECHNICAL_SHOULDER = Gear(GearSet.NONE, R.drawable.technical_shoulder, listOf(
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))

        val WHITE_INDEX_ARM = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_hand, listOf(
            Effect(-24, true, R.string.spread_in_focus_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(-24, true, R.string.spread_in_focus_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -24),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))
        val ARM_PART = Gear(GearSet.PARTS, R.drawable.arm_part, listOf(
            Effect(6, false, R.string.add_patrons_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(6, false, R.string.add_patrons_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 5, 5, 5, 6),
                    1 to listOf(-5, -10, -15, -20, -25, -30, -35, -37, -37)
                )
            ))
        val ARM_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_arm, listOf(
            Effect(24, false, R.string.fire_range_focus_effect),
            Effect(6, false, R.string.add_patrons_effect)),
            autoFillGearEffects(listOf(
                Effect(24, false, R.string.fire_range_focus_effect),
                Effect(6, false, R.string.add_patrons_effect)
            ),
                mapOf(
                    0 to listOf(10, 12, 14, 16, 18, 20, 22, 23, 24),
                    1 to listOf(1, 2, 3, 4, 5, 5, 5, 5, 6)
                )
            ))
        val ARM_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.arm_heavy_port, listOf(
            Effect(-25, true, R.string.spread_in_not_focus_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_not_focus_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -23, -25),
                    1 to listOf(-5, -10, -15, -20, -25, -30, -35, -37, -37)
                )
            ))
        val BIO_NODE_ARM = Gear(GearSet.BIO_NODE, R.drawable.bio_node_arm, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(-36, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(-36, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -25, -25),
                    1 to listOf(-5, -10, -15, -20, -25, -30, -35, -35, -36)
                )
            ))

        val armIcons = mutableListOf(
            SPIKED_SHOULDER, TACTICAL_GLOVES, REGULAR_SHOULDER_PAD,
            SPECIAL_FORCES_GLOVES, TECHNICAL_SHOULDER
        )

        /** Вещи на ноги **/
        val RUNNERS_BOOT = Gear(GearSet.NONE, R.drawable.runners_boots, listOf(
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(2, 4, 6, 7, 8, 9, 10, 11, 12)
                )
            ))
        val REGULAR_BOOTS = Gear(GearSet.NONE, R.drawable.regular_boot, listOf(
            Effect(-25, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))
        val PRECISION_IMPLANT = Gear(GearSet.NONE, R.drawable.precision_implant, listOf(
            Effect(30, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(30, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(4, 8, 12, 16, 20, 24, 28, 29, 30)
                )
            ))
        val TECH_KNEE_PADS = Gear(GearSet.NONE, R.drawable.teck_knee_pads, listOf(
            Effect(12, true, R.string.fire_rate_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.fire_rate_effect)
            ),
                mapOf(
                    0 to listOf(2, 4, 6, 7, 8, 9, 10, 11, 12)
                )
            ))
        val TACTICAL_KNEE_PADS = Gear(GearSet.NONE, R.drawable.tactical_knee_pads, listOf(
            Effect(-25, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))

        val WHITE_INDEX_LEG = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_leg, listOf(
            Effect(10, true, R.string.fire_rate_effect),
            Effect(11, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.fire_rate_effect),
                Effect(11, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(3, 4, 5, 6, 7, 8, 9, 9, 10),
                    1 to listOf(2, 4, 6, 7, 8, 9, 10, 11, 11)
                )
            ))
        val LEG_PART = Gear(GearSet.PARTS, R.drawable.leg_part, listOf(
            Effect(11, true, R.string.max_health_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(-36, true, R.string.spread_in_focus_effect),
                Effect(11, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 6, 7, 8, 9, 10, 10, 11),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -25, -25)
                )
            ))
        val LEG_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_boot, listOf(
            Effect(9, false, R.string.piercing_power_effect),
            Effect(-24, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_power_effect),
                Effect(-24, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 8, 9),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -24)
                )
            ))
        val LEG_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.leg_heavy_port, listOf(
            Effect(-37, true, R.string.spread_in_focus_effect),
            Effect(-24, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.spread_in_focus_effect),
                Effect(-24, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -35, -37),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -24)
                )
            ))
        val BIO_NODE_LEG = Gear(GearSet.BIO_NODE, R.drawable.bio_node_leg, listOf(
            Effect(-25, true, R.string.aiming_speed_effect),
            Effect(-25, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.aiming_speed_effect),
                Effect(-25, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -23, -25),
                    1 to listOf(-5, -8, -11, -14, -17, -20, -23, -25, -25)
                )
            ))

        val legIcons = mutableListOf(
            RUNNERS_BOOT, REGULAR_BOOTS, PRECISION_IMPLANT,
            TECH_KNEE_PADS, TACTICAL_KNEE_PADS
        )

        /** Вещи на 5 слот **/
        val THUG_KNUCKLE = Gear(GearSet.NONE, R.drawable.knuckle, listOf(
            Effect(9, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            ))
        val LOCK_AMULET = Gear(GearSet.NONE, R.drawable.lock_amulet, listOf(
            Effect(-17, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(-17, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(-9, -10, -11, -12, -13, -14, -15, -16, -17)
                )
            ))
        val WEDDING_RING = Gear(GearSet.NONE, R.drawable.wedding_ring, listOf(
            Effect(9, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            ))
        val ENERGY_COILS = Gear(GearSet.NONE, R.drawable.energy_coils, listOf(
            Effect(10, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 9, 10)
                )
            ))
        val PERSONAL_ID_RING = Gear(GearSet.NONE, R.drawable.personal_id_ring, listOf(
            Effect(10, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 9, 10)
                )
            ))

        val WHITE_INDEX_COLLAR = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_collar, listOf(
            Effect(10, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 4, 5, 6, 7, 8, 9, 10)
                )
            ))
        val RING_PART = Gear(GearSet.PARTS, R.drawable.ring_part, listOf(
            Effect(-25, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))
        val RING_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_ring, listOf(
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))
        val COLLAR_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.ring_heavy_port, listOf(
            Effect(18, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(18, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(4, 6, 8, 10, 12, 14, 16, 17, 18)
                )
            ))
        val BIO_NODE_RING = Gear(GearSet.BIO_NODE, R.drawable.bio_node_ring, listOf(
            Effect(17, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(17, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 7, 10, 12, 13, 15, 16, 17)
                )
            ))

        val decorIcons = mutableListOf(
            THUG_KNUCKLE, LOCK_AMULET, WEDDING_RING,
            ENERGY_COILS, PERSONAL_ID_RING
        )

        /** Вещи на 6 слот **/
        val GAS_GRENADE = Gear(GearSet.NONE, R.drawable.gas_grenade, listOf(
            Effect(9, true, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            ))
        val EXPLODER = Gear(GearSet.NONE, R.drawable.exploder, listOf(
            Effect(15, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(15, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(7, 8, 9, 10, 11, 12, 13, 14, 15)
                )
            ))
        val SCAN_FLASHLIGHT = Gear(GearSet.NONE, R.drawable.scan, listOf(
            Effect(9, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            ))
        val ECHO_RADAR = Gear(GearSet.NONE, R.drawable.echo_radar, listOf(
            Effect(49, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(49, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(15, 20, 25, 30, 35, 40, 45, 47, 49)
                )
            ))
        val SPY_SPHERE = Gear(GearSet.NONE, R.drawable.sphere, listOf(
            Effect(9, true, R.string.max_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.max_armor_effect)
            ),
                mapOf(
                    0 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            ))

        val WHITE_INDEX_SCOPE = Gear(GearSet.WHITE_INDEX, R.drawable.white_index_scan, listOf(
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(-5, -10, -15, -20, -25, -30, -35, -36, -37)
                )
            ))
        val PAD_PART = Gear(GearSet.PARTS, R.drawable.pad_part, listOf(
            Effect(-25, true, R.string.aiming_speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.aiming_speed_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))
        val RAD_DARK_IMPLANT = Gear(GearSet.DARK_IMPLANT, R.drawable.dark_rad, listOf(
            Effect(17, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(17, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 7, 10, 12, 13, 15, 16, 17)
                )
            ))
        val SCOPE_HEAVY_PORT = Gear(GearSet.HEAVY_PORT, R.drawable.scan_heavy_port, listOf(
            Effect(17, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(17, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 5, 7, 10, 12, 13, 15, 16, 17)
                )
            ))
        val BIO_NODE_RAD = Gear(GearSet.BIO_NODE, R.drawable.bio_node_rad, listOf(
            Effect(-25, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-5, -8, -11, -14, -17, -20, -23, -24, -25)
                )
            ))

        val deviceIcons = mutableListOf(
            GAS_GRENADE, EXPLODER, SCAN_FLASHLIGHT,
            ECHO_RADAR, SPY_SPHERE
        )

        fun getSetsGears(hero: Hero) : Map<GearCell, Gear> {
            val cells = listOf(
                GearCell.HEAD, GearCell.BODY,
                GearCell.ARM, GearCell.LEG,
                GearCell.DECOR, GearCell.DEVICE
            )
            val list = if (HeroList.SHOTGUNS.contains(hero))
                listOf(WHITE_INDEX_EYE, WHITE_INDEX_HEART,
                    WHITE_INDEX_ARM, WHITE_INDEX_LEG,
                    WHITE_INDEX_COLLAR, WHITE_INDEX_SCOPE
                )
            else if (HeroList.SCOUTS.contains(hero))
                listOf(EYE_PART, HEART_PART,
                    ARM_PART, LEG_PART,
                    RING_PART, PAD_PART
                )
            else if (HeroList.SNIPERS.contains(hero))
                listOf(EYE_DARK_IMPLANT, HEART_DARK_IMPLANT,
                    ARM_DARK_IMPLANT, LEG_DARK_IMPLANT,
                    RING_DARK_IMPLANT, RAD_DARK_IMPLANT
                )
            else if (HeroList.TANKS.contains(hero))
                listOf(EYE_HEAVY_PORT, HEART_HEAVY_PORT,
                    ARM_HEAVY_PORT, LEG_HEAVY_PORT,
                    COLLAR_HEAVY_PORT, SCOPE_HEAVY_PORT
                )
            else listOf(BIO_NODE_EYE, BIO_NODE_HEART,
                BIO_NODE_ARM, BIO_NODE_LEG,
                BIO_NODE_RING, BIO_NODE_RAD
            )
            return cells.mapIndexed { i, cell ->
                cell to list[i]
            }.toMap()
        }

        val allDefaultGears = headIcon + bodyIcons + armIcons + legIcons + decorIcons + deviceIcons

        /** Персональные шмотки **/
        val ARNIE_CAP = Gear(GearSet.PERSONAL, R.drawable.arnie_cap, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(-26, true, R.string.spread_in_not_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(-26, true, R.string.spread_in_not_focus_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26)
                )
            )
        )
        val ARNIE_BANDOLIER = Gear(GearSet.PERSONAL, R.drawable.arnie_bandolier, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(25, false, R.string.fire_range_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(25, false, R.string.fire_range_effect)
            ),
                mapOf(
                    0 to listOf(53, 53, 56, 56, 59, 59, 62, 62, 65),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val ARNIE_BANDAGE = Gear(GearSet.PERSONAL, R.drawable.arnie_bandage, listOf(
            Effect(10, false, R.string.piercing_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val ARNIE_BOOTS = Gear(GearSet.PERSONAL, R.drawable.arnie_boot, listOf(
            Effect(12, true, R.string.armor_damage_effect),
            Effect(-26, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.armor_damage_effect),
                Effect(-26, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26)
                )
            )
        )
        val ARNIE_PONCHO = Gear(GearSet.PERSONAL, R.drawable.arnie_poncho, listOf(
            Effect(18, false, R.string.add_armor_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_armor_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val ARNIE_ROTOR = Gear(GearSet.PERSONAL, R.drawable.arnie_rotor, listOf(
            Effect(11, true, R.string.health_damage_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.health_damage_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )

        val ARNIE_PERSONAL = listOf(
            ARNIE_CAP, ARNIE_BANDOLIER,
            ARNIE_BANDAGE, ARNIE_BOOTS,
            ARNIE_PONCHO, ARNIE_ROTOR
        )

        val CYCLOPS_EYE = Gear(GearSet.PERSONAL, R.drawable.cyclops_eye, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val CYCLOPS_HEART = Gear(GearSet.PERSONAL, R.drawable.cyclops_heart, listOf(
            Effect(11, true, R.string.fire_rate_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.fire_rate_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val CYCLOPS_GLOVES = Gear(GearSet.PERSONAL, R.drawable.cyclops_gloves, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val CYCLOPS_BOOTS = Gear(GearSet.PERSONAL, R.drawable.cyclops_boot, listOf(
            Effect(-30, true, R.string.aiming_speed_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-30, true, R.string.aiming_speed_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-18, -18, -21, -21, -24, -24, -27, -27, -30),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val CYCLOPS_COIN = Gear(GearSet.PERSONAL, R.drawable.cyclops_coin, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(53, 53, 56, 56, 59, 59, 62, 62, 65),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val CYCLOPS_RADAR = Gear(GearSet.PERSONAL, R.drawable.cyclops_radar, listOf(
            Effect(10, false, R.string.piercing_power_effect),
            Effect(10, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_power_effect),
                Effect(10, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )

        val CYCLOPS_PERSONAL = listOf(
            CYCLOPS_EYE, CYCLOPS_HEART,
            CYCLOPS_GLOVES, CYCLOPS_BOOTS,
            CYCLOPS_COIN, CYCLOPS_RADAR
        )

        val SPARKLE_EYE = Gear(GearSet.PERSONAL, R.drawable.sparkle_eye, listOf(
            Effect(-24, true, R.string.spread_in_not_focus_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(-24, true, R.string.spread_in_not_focus_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -24),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val SPARKLE_BELT = Gear(GearSet.PERSONAL, R.drawable.sparkle_belt, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val SPARKLE_GLOVES = Gear(GearSet.PERSONAL, R.drawable.sparkle_gloves, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val SPARKLE_BOOTS = Gear(GearSet.PERSONAL, R.drawable.sparkle_boot, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val SPARKLE_CHOKER = Gear(GearSet.PERSONAL, R.drawable.sparkle_chocker, listOf(
            Effect(18, false, R.string.add_armor_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_armor_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val SPARKLE_GRENADE = Gear(GearSet.PERSONAL, R.drawable.sparkle_grenade, listOf(
            Effect(10, false, R.string.piercing_power_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_power_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )

        val SPARKLE_PERSONAL = listOf(
            SPARKLE_EYE, SPARKLE_BELT,
            SPARKLE_GLOVES, SPARKLE_BOOTS,
            SPARKLE_CHOKER, SPARKLE_GRENADE
        )

        val HURRICANE_EYE = Gear(GearSet.PERSONAL, R.drawable.hurricane_eye, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val HURRICANE_BELT = Gear(GearSet.PERSONAL, R.drawable.hurricane_belt, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val HURRICANE_HANDS = Gear(GearSet.PERSONAL, R.drawable.hurricane_gloves, listOf(
            Effect(10, false, R.string.piercing_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val HURRICANE_BOOTS = Gear(GearSet.PERSONAL, R.drawable.hurricane_boot, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(12, true, R.string.fire_rate_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(12, true, R.string.fire_rate_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val HURRICANE_RING = Gear(GearSet.PERSONAL, R.drawable.hurricane_ring, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(11, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(11, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(5, 6, 6, 8, 8, 9, 9, 11, 11)
                )
            )
        )
        val HURRICANE_CRYSTAL = Gear(GearSet.PERSONAL, R.drawable.hurricane_crystal, listOf(
            Effect(10, false, R.string.piercing_power_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_power_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )

        val HURRICANE_PERSONAL = listOf(
            HURRICANE_EYE, HURRICANE_BELT,
            HURRICANE_HANDS, HURRICANE_BOOTS,
            HURRICANE_RING, HURRICANE_CRYSTAL
        )

        val GHOST_EYE = Gear(GearSet.PERSONAL, R.drawable.ghost_eye, listOf(
            Effect(11, true, R.string.fire_rate_effect),
            Effect(-37, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.fire_rate_effect),
                Effect(-37, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val GHOST_HEART = Gear(GearSet.PERSONAL, R.drawable.ghost_heart, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(10, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(10, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val GHOST_KNUCKLES = Gear(GearSet.PERSONAL, R.drawable.ghost_knuckle, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val GHOST_BOOTS = Gear(GearSet.PERSONAL, R.drawable.ghost_boot, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val GHOST_NECKLACE = Gear(GearSet.PERSONAL, R.drawable.ghost_necklace, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(9, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(9, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9)
                )
            )
        )
        val GHOST_DYNAMO = Gear(GearSet.PERSONAL, R.drawable.ghost_dynamo, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(-26, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(-26, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26)
                )
            )
        )

        val GHOST_PERSONAL = listOf(
            GHOST_EYE, GHOST_HEART,
            GHOST_KNUCKLES, GHOST_BOOTS,
            GHOST_NECKLACE, GHOST_DYNAMO
        )

        val FREDDIE_MASK = Gear(GearSet.PERSONAL, R.drawable.freddie_mask, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            ))
        val FREDDIE_BANDOLIER = Gear(GearSet.PERSONAL, R.drawable.freddie_bandolier, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val FREDDIE_KNUCKLES = Gear(GearSet.PERSONAL, R.drawable.freddie_knuckle, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9)
                )
            )
        )
        val FREDDIE_BOOTS = Gear(GearSet.PERSONAL, R.drawable.freddie_boot, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val FREDDIE_RING = Gear(GearSet.PERSONAL, R.drawable.freddie_ring, listOf(
            Effect(25, false, R.string.fire_range_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val FREDDIE_GRENADE = Gear(GearSet.PERSONAL, R.drawable.freddie_grenade, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )

        val FREDDIE_PERSONAL = listOf(
            FREDDIE_MASK, FREDDIE_BANDOLIER,
            FREDDIE_KNUCKLES, FREDDIE_BOOTS,
            FREDDIE_RING, FREDDIE_GRENADE
        )

        val ANGEL_EYE = Gear(GearSet.PERSONAL, R.drawable.angel_eye, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val ANGEL_HEART = Gear(GearSet.PERSONAL, R.drawable.angel_heart, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val ANGEL_GLOVES = Gear(GearSet.PERSONAL, R.drawable.angel_gloves, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val ANGEL_LEGS = Gear(GearSet.PERSONAL, R.drawable.angel_leg, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val ANGEL_RING = Gear(GearSet.PERSONAL, R.drawable.angel_ring, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(11, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(11, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26),
                    1 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11)
                )
            )
        )
        val ANGEL_SPHERE = Gear(GearSet.PERSONAL, R.drawable.angel_sphere, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )

        val ANGEL_PERSONAL = listOf(
            ANGEL_EYE, ANGEL_HEART,
            ANGEL_GLOVES, ANGEL_LEGS,
            ANGEL_RING, ANGEL_SPHERE
        )

        val RAVEN_MASK = Gear(GearSet.PERSONAL, R.drawable.raven_mask, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val RAVEN_HEART = Gear(GearSet.PERSONAL, R.drawable.raven_heart, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val RAVEN_GLOVES = Gear(GearSet.PERSONAL, R.drawable.raven_gloves, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(11, true, R.string.armor_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(11, true, R.string.armor_damage_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(5, 6, 6, 8, 8, 9, 9, 11, 11)
                )
            )
        )
        val RAVEN_BOOTS = Gear(GearSet.PERSONAL, R.drawable.raven_boot, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val RAVEN_NECKLACE = Gear(GearSet.PERSONAL, R.drawable.raven_necklace, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val RAVEN_RADAR = Gear(GearSet.PERSONAL, R.drawable.raven_radar, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(19, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(19, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(11, 13, 13, 15, 15, 17, 17, 19, 19)
                )
            )
        )

        val RAVEN_PERSONAL = listOf(
            RAVEN_MASK, RAVEN_HEART,
            RAVEN_GLOVES, RAVEN_BOOTS,
            RAVEN_NECKLACE, RAVEN_RADAR
        )

        val BLOT_BRAINPAN = Gear(GearSet.PERSONAL, R.drawable.blot_brainpan, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val BLOT_HEART = Gear(GearSet.PERSONAL, R.drawable.blot_heart, listOf(
            Effect(-37, true, R.string.spread_in_focus_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.spread_in_focus_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val BLOT_SHOULDER = Gear(GearSet.PERSONAL, R.drawable.blot_shoulder, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(-26, true, R.string.aiming_speed_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(-26, true, R.string.aiming_speed_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26)
                )
            )
        )
        val BLOT_LEGS = Gear(GearSet.PERSONAL, R.drawable.blot_leg, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val BLOT_RING = Gear(GearSet.PERSONAL, R.drawable.blot_ring, listOf(
            Effect(10, false, R.string.piercing_power_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_power_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val BLOT_DEVICE = Gear(GearSet.PERSONAL, R.drawable.blot_device, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )

        val BLOT_PERSONAL = listOf(
            BLOT_BRAINPAN, BLOT_HEART,
            BLOT_SHOULDER, BLOT_LEGS,
            BLOT_RING, BLOT_DEVICE
        )

        val FIREFLY_GLASSES = Gear(GearSet.PERSONAL, R.drawable.firefly_glasses, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val FIREFLY_HEART = Gear(GearSet.PERSONAL, R.drawable.firefly_heart, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val FIREFLY_KNUCKLES = Gear(GearSet.PERSONAL, R.drawable.firefly_knuckle, listOf(
            Effect(11, true, R.string.fire_rate_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.fire_rate_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9)
                )
            )
        )
        val FIREFLY_BOOTS = Gear(GearSet.PERSONAL, R.drawable.firefly_boot, listOf(
            Effect(-26, true, R.string.spread_in_focus_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_focus_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val FIREFLY_NECKLACE = Gear(GearSet.PERSONAL, R.drawable.firefly_necklace, listOf(
            Effect(-26, true, R.string.aiming_speed_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.aiming_speed_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val FIREFLY_GRENADE = Gear(GearSet.PERSONAL, R.drawable.firefly_grenade, listOf(
            Effect(11, true, R.string.health_damage_effect),
            Effect(-26, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.health_damage_effect),
                Effect(-26, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26)
                )
            )
        )

        val FIREFLY_PERSONAL = listOf(
            FIREFLY_GLASSES, FIREFLY_HEART,
            FIREFLY_KNUCKLES, FIREFLY_BOOTS,
            FIREFLY_NECKLACE, FIREFLY_GRENADE
        )

        val SLAYER_HEADPHONES = Gear(GearSet.PERSONAL, R.drawable.slayer_headphones, listOf(
            Effect(11, true, R.string.fire_rate_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.fire_rate_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val SLAYER_POUCH = Gear(GearSet.PERSONAL, R.drawable.slayer_pouch, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(-26, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(-26, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26)
                )
            )
        )
        val SLAYER_BANDAGE = Gear(GearSet.PERSONAL, R.drawable.slayer_bandage, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val SLAYER_BOOTS = Gear(GearSet.PERSONAL, R.drawable.slayer_boot, listOf(
            Effect(-37, true, R.string.spread_in_focus_effect),
            Effect(10, false, R.string.piercing_power_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.spread_in_focus_effect),
                Effect(10, false, R.string.piercing_power_effect)
            ),
                mapOf(
                    0 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37),
                    1 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10)
                )
            )
        )
        val SLAYER_TAGS = Gear(GearSet.PERSONAL, R.drawable.slayer_tags, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val SLAYER_RADAR = Gear(GearSet.PERSONAL, R.drawable.slayer_rad, listOf(
            Effect(18, false, R.string.add_armor_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_armor_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )

        val SLAYER_PERSONAL = listOf(
            SLAYER_HEADPHONES, SLAYER_POUCH,
            SLAYER_BANDAGE, SLAYER_BOOTS,
            SLAYER_TAGS, SLAYER_RADAR
        )

        val MIRAGE_EYE = Gear(GearSet.PERSONAL, R.drawable.mirage_eye, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val MIRAGE_BELT = Gear(GearSet.PERSONAL, R.drawable.mirage_belt, listOf(
            Effect(9, true, R.string.speed_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.speed_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18)
                )
            )
        )
        val MIRAGE_KNUCKLES = Gear(GearSet.PERSONAL, R.drawable.mirage_knuckle, listOf(
            Effect(-37, true, R.string.time_up_bust_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.time_up_bust_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37),
                    1 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25)
                )
            )
        )
        val MIRAGE_BOOTS = Gear(GearSet.PERSONAL, R.drawable.mirage_boot, listOf(
            Effect(6, false, R.string.add_patrons_effect),
            Effect(12, true, R.string.fire_rate_effect)),
            autoFillGearEffects(listOf(
                Effect(6, false, R.string.add_patrons_effect),
                Effect(12, true, R.string.fire_rate_effect)
            ),
                mapOf(
                    0 to listOf(2, 3, 3, 4, 4, 5, 5, 6, 6),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val MIRAGE_CHOKER = Gear(GearSet.PERSONAL, R.drawable.mirage_choker, listOf(
            Effect(18, false, R.string.add_health_effect),
            Effect(10, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_health_effect),
                Effect(10, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18),
                    1 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10)
                )
            )
        )
        val MIRAGE_ANTIGRAV = Gear(GearSet.PERSONAL, R.drawable.mirage_antigrav, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(7, true, R.string.max_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(7, true, R.string.max_armor_effect)
            ),
                mapOf(
                    0 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65),
                    1 to listOf(3, 3, 4, 4, 5, 5, 6, 6, 7)
                )
            )
        )

        val MIRAGE_PERSONAL = listOf(
            MIRAGE_EYE, MIRAGE_BELT,
            MIRAGE_KNUCKLES, MIRAGE_BOOTS,
            MIRAGE_CHOKER, MIRAGE_ANTIGRAV
        )

        val LYNX_VISOR = Gear(GearSet.PERSONAL, R.drawable.lynx_visor, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(-26, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(-26, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65),
                    1 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26)
                )
            )
        )
        val LYNX_QUIVER = Gear(GearSet.PERSONAL, R.drawable.lynx_quiver, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val LYNX_GLOVES = Gear(GearSet.PERSONAL, R.drawable.lynx_gloves, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9),
                    1 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25)
                )
            )
        )
        val LYNX_KNEEPADS = Gear(GearSet.PERSONAL, R.drawable.lynx_knee_pads, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val LYNX_ARROWHEAD = Gear(GearSet.PERSONAL, R.drawable.lynx_arrowhead, listOf(
            Effect(18, false, R.string.add_health_effect),
            Effect(10, true, R.string.health_damage_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_health_effect),
                Effect(10, true, R.string.health_damage_effect)
            ),
                mapOf(
                    0 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18),
                    1 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10)
                )
            )
        )
        val LYNX_EARBUDS = Gear(GearSet.PERSONAL, R.drawable.lynx_earbuds, listOf(
            Effect(-26, true, R.string.spread_in_focus_effect),
            Effect(-25, true, R.string.aiming_speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_focus_effect),
                Effect(-25, true, R.string.aiming_speed_effect)
            ),
                mapOf(
                    0 to listOf(-14, -17, -17, -20, -20, -23, -23, -26, -26),
                    1 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25)
                )
            )
        )

        val LYNX_PERSONAL = listOf(
            LYNX_VISOR, LYNX_QUIVER,
            LYNX_GLOVES, LYNX_KNEEPADS,
            LYNX_ARROWHEAD, LYNX_EARBUDS
        )

        val SMOG_MASK = Gear(GearSet.PERSONAL, R.drawable.smog_mask, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(25, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(25, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(53, 53, 56, 56, 59, 59, 62, 62, 65),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val SMOG_HEART = Gear(GearSet.PERSONAL, R.drawable.smog_heart, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(10, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(10, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val SMOG_GLOVES = Gear(GearSet.PERSONAL, R.drawable.smog_gloves, listOf(
            Effect(25, false, R.string.fire_range_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val SMOG_BOOTS = Gear(GearSet.PERSONAL, R.drawable.smog_boot, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val SMOG_TAG = Gear(GearSet.PERSONAL, R.drawable.smog_tag, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val SMOG_ROCKET = Gear(GearSet.PERSONAL, R.drawable.smog_rocket, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(-26, true, R.string.spread_in_move_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(-26, true, R.string.spread_in_move_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26)
                )
            )
        )

        val SMOG_PERSONAL = listOf(
            SMOG_MASK, SMOG_HEART,
            SMOG_GLOVES, SMOG_BOOTS,
            SMOG_TAG, SMOG_ROCKET
        )

        val BASTION_EYE = Gear(GearSet.PERSONAL, R.drawable.bastion_eye, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65),
                    1 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25)
                )
            )
        )
        val BASTION_HEART = Gear(GearSet.PERSONAL, R.drawable.bastion_heart, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9)
                )
            )
        )
        val BASTION_GLOVES = Gear(GearSet.PERSONAL, R.drawable.bastion_gloves, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(10, true, R.string.fire_rate_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(10, true, R.string.fire_rate_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val BASTION_BOOTS = Gear(GearSet.PERSONAL, R.drawable.bastion_boot, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val BASTION_RING = Gear(GearSet.PERSONAL, R.drawable.bastion_ring, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(-26, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(-26, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-14, -17, -17, -21, -21, -23, -23, -26, -26)
                )
            )
        )
        val BASTION_SPHERE = Gear(GearSet.PERSONAL, R.drawable.bastion_sphere, listOf(
            Effect(18, false, R.string.add_health_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_health_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )

        val BASTION_PERSONAL = listOf(
            BASTION_EYE, BASTION_HEART,
            BASTION_GLOVES, BASTION_BOOTS,
            BASTION_RING, BASTION_SPHERE
        )

        val DRAGOON_HAT = Gear(GearSet.PERSONAL, R.drawable.dragoon_hat, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val DRAGOON_HEART = Gear(GearSet.PERSONAL, R.drawable.dragoon_heart, listOf(
            Effect(10, false, R.string.piercing_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val DRAGOON_SHOULDER_PAD = Gear(GearSet.PERSONAL, R.drawable.dragoon_shoulder_pad, listOf(
            Effect(5, false, R.string.add_patrons_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(5, false, R.string.add_patrons_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(1, 1, 2, 2, 3, 3, 4, 4, 5),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val DRAGOON_BOOTS = Gear(GearSet.PERSONAL, R.drawable.dragoon_boot, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val DRAGOON_CHAIN = Gear(GearSet.PERSONAL, R.drawable.dragoon_chain, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val DRAGOON_ROTOR = Gear(GearSet.PERSONAL, R.drawable.dragoon_rotor, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )

        val DRAGOON_PERSONAL = listOf(
            DRAGOON_HAT, DRAGOON_HEART,
            DRAGOON_SHOULDER_PAD, DRAGOON_BOOTS,
            DRAGOON_CHAIN, DRAGOON_ROTOR
        )

        val BERTHA_EYE = Gear(GearSet.PERSONAL, R.drawable.bertha_eye, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val BERTHA_BANDOLIER = Gear(GearSet.PERSONAL, R.drawable.bertha_belt, listOf(
            Effect(10, false, R.string.piercing_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(10, false, R.string.piercing_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val BERTHA_SLEEVE = Gear(GearSet.PERSONAL, R.drawable.bertha_sleeve, listOf(
            Effect(18, false, R.string.add_armor_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_armor_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val BERTHA_BOOTS = Gear(GearSet.PERSONAL, R.drawable.bertha_boot, listOf(
            Effect(-25, true, R.string.reloading_time_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-25, true, R.string.reloading_time_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-17, -17, -19, -19, -21, -21, -23, -23, -25),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val BERTHA_RING = Gear(GearSet.PERSONAL, R.drawable.bertha_ring, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(18, false, R.string.add_health_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(18, false, R.string.add_health_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val BERTHA_FLASHLIGHT = Gear(GearSet.PERSONAL, R.drawable.bertha_scan, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )

        val BERTHA_PERSONAL = listOf(
            BERTHA_EYE, BERTHA_BANDOLIER,
            BERTHA_SLEEVE, BERTHA_BOOTS,
            BERTHA_RING, BERTHA_FLASHLIGHT
        )

        val LEVIATHAN_HAT = Gear(GearSet.PERSONAL, R.drawable.leviathan_cap, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(8, false, R.string.piercing_power_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(8, false, R.string.piercing_power_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(4, 5, 5, 6, 6, 7, 7, 8, 8)
                )
            )
        )
        val LEVIATHAN_IMPLANT = Gear(GearSet.PERSONAL, R.drawable.leviathan_implant, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val LEVIATHAN_SHOULDER_PAD = Gear(GearSet.PERSONAL, R.drawable.leviathan_shoulder, listOf(
            Effect(9, true, R.string.damage_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(9, true, R.string.damage_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val LEVIATHAN_BOOTS = Gear(GearSet.PERSONAL, R.drawable.leviathan_boot, listOf(
            Effect(12, true, R.string.fire_rate_effect),
            Effect(12, true, R.string.max_health_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.fire_rate_effect),
                Effect(12, true, R.string.max_health_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val LEVIATHAN_RING = Gear(GearSet.PERSONAL, R.drawable.leviathan_ring, listOf(
            Effect(11, true, R.string.health_damage_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.health_damage_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(5, 6, 6, 8, 8, 9, 9, 11, 11),
                    1 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18)
                )
            )
        )
        val LEVIATHAN_EXPLODER = Gear(GearSet.PERSONAL, R.drawable.leviathan_exploder, listOf(
            Effect(65, false, R.string.visibility_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(65, false, R.string.visibility_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65),
                    1 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9)
                )
            )
        )

        val LEVIATHAN_PERSONAL = listOf(
            LEVIATHAN_HAT, LEVIATHAN_IMPLANT,
            LEVIATHAN_SHOULDER_PAD, LEVIATHAN_BOOTS,
            LEVIATHAN_RING, LEVIATHAN_EXPLODER
        )

        val STALKER_HAT = Gear(GearSet.PERSONAL, R.drawable.stalker_hat, listOf(
            Effect(25, false, R.string.fire_range_focus_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(25, false, R.string.fire_range_focus_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(17, 17, 19, 19, 21, 21, 23, 23, 25),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val STALKER_BELT = Gear(GearSet.PERSONAL, R.drawable.stalker_belt, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(10, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(10, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val STALKER_SHOULDER_PAD = Gear(GearSet.PERSONAL, R.drawable.stalker_shoulder_pad, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(18, false, R.string.add_armor_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(18, false, R.string.add_armor_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18)
                )
            )
        )
        val STALKER_BOOTS = Gear(GearSet.PERSONAL, R.drawable.stalker_boot, listOf(
            Effect(12, true, R.string.fire_rate_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.fire_rate_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val STALKER_CHAIN = Gear(GearSet.PERSONAL, R.drawable.stalker_chain, listOf(
            Effect(10, true, R.string.health_damage_effect),
            Effect(-37, true, R.string.time_up_ammo_effect)),
            autoFillGearEffects(listOf(
                Effect(10, true, R.string.health_damage_effect),
                Effect(-37, true, R.string.time_up_ammo_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val STALKER_RAZOR = Gear(GearSet.PERSONAL, R.drawable.stalker_razor, listOf(
            Effect(6, false, R.string.add_patrons_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(6, false, R.string.add_patrons_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(2, 2, 3, 3, 4, 4, 5, 5, 6),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )

        val STALKER_PERSONAL = listOf(
            STALKER_HAT, STALKER_BELT,
            STALKER_SHOULDER_PAD, STALKER_BOOTS,
            STALKER_CHAIN, STALKER_RAZOR
        )

        val DOC_MASK = Gear(GearSet.PERSONAL, R.drawable.doc_mask, listOf(
            Effect(-26, true, R.string.spread_in_not_focus_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_not_focus_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val DOC_BELT = Gear(GearSet.PERSONAL, R.drawable.doc_belt, listOf(
            Effect(18, false, R.string.add_health_effect),
            Effect(-37, true, R.string.spread_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_health_effect),
                Effect(-37, true, R.string.spread_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(10, 10, 12, 12, 14, 14, 16, 16, 18),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val DOC_GLOVES = Gear(GearSet.PERSONAL, R.drawable.doc_gloves, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val DOC_BOOTS = Gear(GearSet.PERSONAL, R.drawable.doc_boot, listOf(
            Effect(12, true, R.string.speed_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.speed_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val DOC_NECKLACE = Gear(GearSet.PERSONAL, R.drawable.doc_necklace, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(19, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(19, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(11, 13, 13, 15, 15, 17, 17, 19, 19)
                )
            )
        )
        val DOC_ROCKET = Gear(GearSet.PERSONAL, R.drawable.doc_rocket, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(3, 3, 4, 4, 6, 6, 7, 7, 9),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )

        val DOC_PERSONAL = listOf(
            DOC_MASK, DOC_BELT,
            DOC_GLOVES, DOC_BOOTS,
            DOC_NECKLACE, DOC_ROCKET
        )

        val LEVI_PATCH = Gear(GearSet.PERSONAL, R.drawable.levi_patch, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(25, false, R.string.fire_range_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(25, false, R.string.fire_range_focus_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -21, -21, -23, -23, -26),
                    1 to listOf(17, 19, 19, 21, 21, 23, 23, 25, 25)
                )
            )
        )
        val LEVI_BELT = Gear(GearSet.PERSONAL, R.drawable.levi_belt, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(10, false, R.string.piercing_power_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(10, false, R.string.piercing_power_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val LEVI_WHIP = Gear(GearSet.PERSONAL, R.drawable.levi_whip, listOf(
            Effect(-26, true, R.string.aiming_speed_effect),
            Effect(9, false, R.string.piercing_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.aiming_speed_effect),
                Effect(9, false, R.string.piercing_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(3, 4, 4, 6, 6, 7, 7, 9, 9)
                )
            )
        )
        val LEVI_BOOTS = Gear(GearSet.PERSONAL, R.drawable.levi_boot, listOf(
            Effect(-26, true, R.string.spread_in_focus_effect),
            Effect(12, true, R.string.fire_rate_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_focus_effect),
                Effect(12, true, R.string.fire_rate_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(6, 7, 7, 9, 9, 10, 10, 12, 12)
                )
            )
        )
        val LEVI_TAG = Gear(GearSet.PERSONAL, R.drawable.levi_tag, listOf(
            Effect(18, false, R.string.add_armor_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(18, false, R.string.add_armor_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(10, 12, 12, 14, 14, 16, 16, 18, 18),
                    1 to listOf(53, 53, 56, 56, 59, 59, 62, 62, 65)
                )
            )
        )
        val LEVI_RADIO = Gear(GearSet.PERSONAL, R.drawable.levi_radio, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )

        val LEVI_PERSONAL = listOf(
            LEVI_PATCH, LEVI_BELT,
            LEVI_WHIP, LEVI_BOOTS,
            LEVI_TAG, LEVI_RADIO
        )

        val SATOSHI_EYE = Gear(GearSet.PERSONAL, R.drawable.satoshi_eye, listOf(
            Effect(12, true, R.string.max_armor_effect),
            Effect(-25, true, R.string.reloading_time_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_armor_effect),
                Effect(-25, true, R.string.reloading_time_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )
        val SATOSHI_SHOULDER_PAD = Gear(GearSet.PERSONAL, R.drawable.satoshi_should, listOf(
            Effect(9, false, R.string.piercing_effect),
            Effect(19, true, R.string.speed_in_focus_effect)),
            autoFillGearEffects(listOf(
                Effect(9, false, R.string.piercing_effect),
                Effect(19, true, R.string.speed_in_focus_effect)
            ),
                mapOf(
                    0 to listOf(4, 4, 5, 5, 7, 7, 8, 8, 10),
                    1 to listOf(4, 5, 5, 7, 7, 8, 8, 10, 10)
                )
            )
        )
        val SATOSHI_HANDS = Gear(GearSet.PERSONAL, R.drawable.satoshi_hands, listOf(
            Effect(12, true, R.string.max_health_effect),
            Effect(-37, true, R.string.time_up_bust_effect)),
            autoFillGearEffects(listOf(
                Effect(12, true, R.string.max_health_effect),
                Effect(-37, true, R.string.time_up_bust_effect)
            ),
                mapOf(
                    0 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12),
                    1 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37)
                )
            )
        )
        val SATOSHI_LEGS = Gear(GearSet.PERSONAL, R.drawable.satoshi_leg, listOf(
            Effect(-37, true, R.string.spread_in_focus_effect),
            Effect(12, true, R.string.speed_effect)),
            autoFillGearEffects(listOf(
                Effect(-37, true, R.string.spread_in_focus_effect),
                Effect(12, true, R.string.speed_effect)
            ),
                mapOf(
                    0 to listOf(-25, -28, -28, -31, -31, -34, -34, -37, -37),
                    1 to listOf(6, 6, 7, 7, 9, 9, 10, 10, 12)
                )
            )
        )
        val SATOSHI_RING = Gear(GearSet.PERSONAL, R.drawable.satoshi_ring, listOf(
            Effect(-26, true, R.string.spread_in_move_effect),
            Effect(65, false, R.string.visibility_effect)),
            autoFillGearEffects(listOf(
                Effect(-26, true, R.string.spread_in_move_effect),
                Effect(65, false, R.string.visibility_effect)
            ),
                mapOf(
                    0 to listOf(-14, -14, -17, -17, -20, -20, -23, -23, -26),
                    1 to listOf(53, 56, 56, 59, 59, 62, 62, 65, 65)
                )
            )
        )
        val SATOSHI_ORB = Gear(GearSet.PERSONAL, R.drawable.satoshi_sphere, listOf(
            Effect(11, true, R.string.armor_damage_effect),
            Effect(-25, true, R.string.running_volume_effect)),
            autoFillGearEffects(listOf(
                Effect(11, true, R.string.armor_damage_effect),
                Effect(-25, true, R.string.running_volume_effect)
            ),
                mapOf(
                    0 to listOf(5, 5, 6, 6, 8, 8, 9, 9, 11),
                    1 to listOf(-17, -19, -19, -21, -21, -23, -23, -25, -25)
                )
            )
        )

        val SATOSHI_PERSONAL = listOf(
            SATOSHI_EYE, SATOSHI_SHOULDER_PAD,
            SATOSHI_HANDS, SATOSHI_LEGS,
            SATOSHI_RING, SATOSHI_ORB
        )

        val allGears = allDefaultGears +
                listOf(
                    WHITE_INDEX_EYE, WHITE_INDEX_HEART,
                    WHITE_INDEX_ARM, WHITE_INDEX_LEG,
                    WHITE_INDEX_COLLAR, WHITE_INDEX_SCOPE
                ) +
                listOf(
                    EYE_PART, HEART_PART,
                    ARM_PART, LEG_PART,
                    RING_PART, PAD_PART
                ) +
                listOf(
                    EYE_DARK_IMPLANT, HEART_DARK_IMPLANT,
                    ARM_DARK_IMPLANT, LEG_DARK_IMPLANT,
                    RING_DARK_IMPLANT, RAD_DARK_IMPLANT
                ) +
                listOf(
                    EYE_HEAVY_PORT, HEART_HEAVY_PORT,
                    ARM_HEAVY_PORT, LEG_HEAVY_PORT,
                    COLLAR_HEAVY_PORT, SCOPE_HEAVY_PORT
                ) +
                listOf(
                    BIO_NODE_EYE, BIO_NODE_HEART,
                    BIO_NODE_ARM, BIO_NODE_LEG,
                    BIO_NODE_RING, BIO_NODE_RAD
                ) +
                ARNIE_PERSONAL + CYCLOPS_PERSONAL +
                SPARKLE_PERSONAL + HURRICANE_PERSONAL +
                GHOST_PERSONAL + FREDDIE_PERSONAL +
                ANGEL_PERSONAL + RAVEN_PERSONAL +
                BLOT_PERSONAL + FIREFLY_PERSONAL +
                SLAYER_PERSONAL + LYNX_PERSONAL +
                MIRAGE_PERSONAL + SMOG_PERSONAL +
                BASTION_PERSONAL + DRAGOON_PERSONAL +
                BERTHA_PERSONAL + LEVIATHAN_PERSONAL +
                DOC_PERSONAL + STALKER_PERSONAL +
                LEVI_PERSONAL + SATOSHI_PERSONAL

        fun getEffectsFromSets(gears: List<Gear>) : List<Effect> {
            val effects = mutableListOf<Effect>()
            when (gears.filter { it.gearSet == GearSet.BIO_NODE }.size) {
                2, 3 -> effects.add(Effect(2, false, R.string.piercing_effect))
                4, 5 -> {
                    effects.add(Effect(2, false, R.string.piercing_effect))
                    effects.add(Effect(3, false, R.string.add_patrons_effect))
                }
                6 -> {
                    effects.add(Effect(2, false, R.string.piercing_effect))
                    effects.add(Effect(3, false, R.string.add_patrons_effect))
                    effects.add(Effect(-11, true, R.string.running_volume_effect))
                }
            }
            when (gears.filter { it.gearSet == GearSet.DARK_IMPLANT }.size) {
                2, 3 -> effects.add(Effect(5, true, R.string.health_damage_effect))
                4, 5 -> {
                    effects.add(Effect(5, true, R.string.health_damage_effect))
                    effects.add(Effect(10, false, R.string.fire_range_focus_effect))
                }
                6 -> {
                    effects.add(Effect(5, true, R.string.health_damage_effect))
                    effects.add(Effect(10, false, R.string.fire_range_focus_effect))
                    effects.add(Effect(-10, true, R.string.reloading_time_effect))
                }
            }
            when (gears.filter { it.gearSet == GearSet.WHITE_INDEX }.size) {
                2, 3 -> effects.add(Effect(5, true, R.string.armor_damage_effect))
                4, 5 -> {
                    effects.add(Effect(5, true, R.string.armor_damage_effect))
                    effects.add(Effect(-10, true, R.string.reloading_time_effect))
                }
                6 -> {
                    effects.add(Effect(5, true, R.string.armor_damage_effect))
                    effects.add(Effect(-10, true, R.string.reloading_time_effect))
                    effects.add(Effect(10, false, R.string.add_health_effect))
                }
            }
            when (gears.filter { it.gearSet == GearSet.HEAVY_PORT }.size) {
                2, 3 -> effects.add(Effect(2, false, R.string.piercing_effect))
                4, 5 -> {
                    effects.add(Effect(2, false, R.string.piercing_effect))
                    effects.add(Effect(3, true, R.string.max_armor_effect))
                }
                6 -> {
                    effects.add(Effect(2, false, R.string.piercing_effect))
                    effects.add(Effect(3, true, R.string.max_armor_effect))
                    effects.add(Effect(5, true, R.string.armor_damage_effect))
                }
            }
            when (gears.filter { it.gearSet == GearSet.PARTS }.size) {
                2, 3 -> effects.add(Effect(5, true, R.string.fire_rate_effect))
                4, 5 -> {
                    effects.add(Effect(5, true, R.string.fire_rate_effect))
                    effects.add(Effect(10, false, R.string.fire_range_focus_effect))
                }
                6 -> {
                    effects.add(Effect(5, true, R.string.fire_rate_effect))
                    effects.add(Effect(10, false, R.string.fire_range_focus_effect))
                    effects.add(Effect(-11, true, R.string.spread_in_not_focus_effect))
                }
            }
            return effects
        }

        fun getMapGearStringToResource(): Map<String, Int> {
            val ignoreFieldNames = listOf("personal", "gear", "icon")
            val isTrueField: (String) -> Boolean = { name ->
                for (ifn in ignoreFieldNames) {
                    if (name.contains(ifn)) {
                        false
                    }
                }
                true
            }
            return Companion::class.java.declaredFields.mapIndexed { i, f ->
                val name = f.name.lowercase(Locale.getDefault())
                if (isTrueField(name))
                    name to allGears[i].icon
                else "" to 0
            }.toMap().filter { it.key.isNotEmpty() }
        }

    }

}