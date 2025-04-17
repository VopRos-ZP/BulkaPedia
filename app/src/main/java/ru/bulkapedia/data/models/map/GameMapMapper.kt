package ru.bulkapedia.data.models.map

import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode

fun GameMapDto.toGameMap() = GameMap(
    id = id,
    name = name,
    imageUrl = imageUrl,
    spawnsUrl = spawnsUrl,
    mode = MapMode.valueOf(mode)
)

fun GameMap.toDto() = GameMapDto(
    id = id,
    name = name,
    imageUrl = imageUrl,
    spawnsUrl = spawnsUrl,
    mode = mode.name
)