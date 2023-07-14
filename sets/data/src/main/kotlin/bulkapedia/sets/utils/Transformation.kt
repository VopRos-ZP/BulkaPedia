package bulkapedia.sets.utils

import bulkapedia.gears.Gear
import bulkapedia.gears.GearCell
import bulkapedia.sets.UserSet
import bulkapedia.sets.UserSetDTO

private fun cellToIcon(cell: GearCell, gears: Map<GearCell, String>): String = gears[cell] ?: Gear.emptyIcon(cell)

fun UserSet.toDTO() = UserSetDTO(
    setId, author, hero,
    cellToIcon(GearCell.HEAD, gears),
    cellToIcon(GearCell.BODY, gears),
    cellToIcon(GearCell.ARM, gears),
    cellToIcon(GearCell.LEG, gears),
    cellToIcon(GearCell.DECOR, gears),
    cellToIcon(GearCell.DEVICE, gears),
    userLikeIds
)

fun UserSetDTO.toPOJO() = UserSet(
    setId, author, hero,
    listOf(head, body, arm, leg, decor, device).mapIndexed { index, s ->
        GearCell.values()[index] to s
    }.toMap(),
    userLikeIds
)