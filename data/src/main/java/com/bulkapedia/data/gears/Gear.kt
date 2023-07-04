package com.bulkapedia.data.gears

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.bulkapedia.data.gears.Effect.Companion.toDTO
import com.bulkapedia.data.gears.dto.EffectDTO
import com.bulkapedia.data.gears.dto.GearDTO
import com.bulkapedia.data.sets.GearCell
import com.google.firebase.firestore.DocumentSnapshot

data class Gear(
    val gearId: String,
    val icon: String,
    val gearCell: String,
    val gearSet: String,
    val effects: List<Effect>,
    val ranks: Map<String, List<Int>>
) {

    companion object {

        val emptyIcons = listOf(
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fhead%2Fempty_head.jpg?alt=media&token=ef3f8ac1-a217-4ff7-a0c1-1116d848e449",
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fbody%2Fempty_body.jpg?alt=media&token=a740ee91-dd44-4801-bccf-09b02694dadb",
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Farm%2Fempty_arm.jpg?alt=media&token=b66f1a68-989a-4457-bed0-bb5c0d1c1573",
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fleg%2Fempty_leg.jpg?alt=media&token=41887b2a-6756-4b00-8680-36c7a982dec4",
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fdecor%2Fempty_decor.jpg?alt=media&token=71d82eb0-3d23-4784-8c65-c58ce934d3ea",
            "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fdevice%2Fempty_device.jpg?alt=media&token=2166d17c-5896-4df5-9f26-93f221ff513b",
        )

        val emptyGears = mapOf(
            GearCell.HEAD to emptyIcons[0],
            GearCell.BODY to emptyIcons[1],
            GearCell.ARM to emptyIcons[2],
            GearCell.LEG to emptyIcons[3],
            GearCell.DECOR to emptyIcons[4],
            GearCell.DEVICE to emptyIcons[5],
        )

        fun EMPTY(cell: GearCell): Gear = Gear("", emptyGears[cell] ?: "", cell.name, "", emptyList(), emptyMap())

        fun DocumentSnapshot.toGear(): Gear? {
            return docToObject(GearDTO::class.java) { dto ->
                Gear(id,
                    dto.icon, dto.gearCell, dto.gearSet,
                    dto.effects.toEffects(), dto.ranks
                )
            }
        }

        private fun List<Effect>.unEffect(): Map<String, EffectDTO> {
            return mapIndexed { i, m -> i.toString() to m.toDTO() }.toMap()
        }

        private fun Map<String, EffectDTO>.toEffects(): List<Effect> {
            return map { (_, map) -> Effect(map.number, map.percent, map.description) }
        }

        fun Gear.getRankEffect(): Map<Ranks, List<Effect>> {
            val rankEffects = ranks.mapKeys { it.key.toInt() }
            return autoFillGearEffects(effects, rankEffects)
        }

        private fun autoFillGearEffects(effects: List<Effect>, ints: Map<Int, List<Int>>): Map<Ranks, List<Effect>> {
            val m = mutableMapOf<Ranks, List<Effect>>()
            Ranks.values().forEachIndexed { i, r ->
                m += r to effects.mapIndexed { ei, it -> Effect(ints[ei]!![i], it.percent, it.description) }
            }
            return m
        }

        private fun Gear.toDTO() = GearDTO(icon, gearCell, gearSet, effects.unEffect(), ranks)

        fun Gear.toData(): Map<String, Any> = toDTO().asMap().mapValues { it }

    }

}