package bulkapedia.gears

enum class GearSet {
    NONE,
    WHITE_INDEX,
    PARTS,
    DARK_IMPLANT,
    HEAVY_PORT,
    BIO_NODE,
    PERSONAL;

    override fun toString(): String {
        return name.lowercase()
    }
}