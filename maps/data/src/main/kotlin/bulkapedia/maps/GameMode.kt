package bulkapedia.maps

enum class GameMode(private val ruName: String) {
    BR("Королевская битва"),
    KoH("Царь горы"),
    SQUAD("Стенка"),
    SABOTAGE("Саботаж");

    override fun toString(): String {
        return ruName
    }

    companion object {
        fun get(ruName: String): GameMode {
            return GameMode.values().first { it.ruName == ruName }
        }
    }
}