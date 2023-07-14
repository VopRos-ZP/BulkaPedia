package bulkapedia.gears

enum class GearCell {
    HEAD, BODY,
    ARM, LEG,
    DECOR, DEVICE;

    override fun toString(): String {
        return name.lowercase()
    }
}