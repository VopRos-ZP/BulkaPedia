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
    }

}