package bulkapedia.gears

import bulkapedia.effects.Effect

data class Gear(
    val gearId: String,
    val cell: GearCell,
    val set: GearSet,
    val icon: String,
    val effects: List<Effect>,
    val ranks: Map<Rank, List<Effect>>
) {

    companion object {
        val emptyIcon = { cell: GearCell -> "empty_$cell" }
        fun EMPTY(cell: GearCell) = Gear("", GearCell.HEAD, GearSet.NONE, emptyIcon(cell), emptyList(), emptyMap())

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
    }

}