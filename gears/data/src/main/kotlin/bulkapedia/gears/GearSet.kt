package bulkapedia.gears

enum class GearSet {
    NONE,
    WHITE_INDEX,
    PARTS,
    DARK_IMPLANT,
    HEAVY_PORT,
    PERSONAL;

    override fun toString(): String {
        return name.lowercase()
    }
}