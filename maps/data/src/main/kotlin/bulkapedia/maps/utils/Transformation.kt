package bulkapedia.maps.utils

import bulkapedia.maps.GameMap
import bulkapedia.maps.GameMapDTO
import bulkapedia.maps.GameMode

fun GameMap.toDTO() = GameMapDTO(mapId, image, spawns, mode.toString(), name)

fun GameMapDTO.toPOJO() = GameMap(mapId, image, spawns, GameMode.get(mode), name)