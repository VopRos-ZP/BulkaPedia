package bulkapedia.maps

data class GameMap(
    val mapId: String,
    val image: String,
    val spawns: String,
    val mode: GameMode,
    val name: String
)
