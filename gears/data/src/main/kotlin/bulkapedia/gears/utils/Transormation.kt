package bulkapedia.gears.utils

import bulkapedia.effects.Effect
import bulkapedia.gears.Gear
import bulkapedia.gears.GearCell
import bulkapedia.gears.GearDTO
import bulkapedia.gears.GearSet
import bulkapedia.gears.Rank

fun GearDTO.toPOJO(): Gear = Gear(
    gearId = id,
    cell = GearCell.valueOf(gearCell.uppercase()),
    set = GearSet.valueOf(gearSet.uppercase()),
    icon = icon,
    effects = effects,
    ranks = ranks.toRank(effects)
)

fun Gear.toDTO(): GearDTO = GearDTO(
    id = gearId,
    gearCell = cell.toString(),
    gearSet = set.toString(),
    icon = icon,
    ranks = ranks.toDTO(),
    effects = effects
)

fun Map<String, List<Int>>.toRank(effects: List<Effect>): Map<Rank, List<Effect>> {
    return map { (key, list) ->
        Rank.valueOf(key.uppercase()) to list.mapIndexed { i, n -> effects[i].copy(number = n) }
    }.toMap()
}

fun Map<Rank, List<Effect>>.toDTO(): Map<String, List<Int>> {
    return map { (rank, effects) -> rank.toString() to effects.map { it.number } }.toMap()
}
