package ru.bulkapedia.data.models.map

import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode

fun GameMapDto.toGameMap() = GameMap(
    id = id,
    original = original,
    spawns = spawns,
    mode = MapMode.valueOf(mode)
)

fun GameMap.toDto() = GameMapDto(
    id = id,
    original = original,
    spawns = spawns,
    mode = mode.name
)