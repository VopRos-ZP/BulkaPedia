package bulkapedia.heroes

data class HeroStats(
    val maxHealth: Int, // здоровье
    val maxArmor: Int, // броня
    val damage: Int, // урон
    val vision: Int, // дальность видимости
    val maxSilence: Int, // макс. радиус шума при движении
    val maxSpeed: Int, // максимальная скорость движения
    val maxSpeedInFocus: Int, // максимальная скорость движения в прицеле
    val heroDensity: Int, // сопротивление пробитию
    val reloadTime: Double, // время перезарядки
    val fireRate: Double, // скорость стрельбы
    val fireRange: Int, // дальность стрельбы (не в прицеле)
    val fireRangeInFocus: Int, // дальность стрельбы (в прицеле)
    val spreadOnStay: Int, // угол разброса
    val spreadInMove: Int, // угол разброса в движении
    val spreadInFocus: Int, // резброс прицельной стрельбы
    val patrons: Int, // кол-во патронов
    val powerPenetration: Int, // пробивная способность
    val damageOnHealth: Double, // модификатор урона по здоровью
    val damageOnArmor: Double, // модификатор урона по броне
    val armorPenetration: Int, // бронебойность
    val focusTime: Double, // время прицеливания
)

fun HeroStats.toMap(): Map<String, Double> {
    return mapOf(
        "maxHealth" to maxHealth.toDouble(),
        "maxArmor" to maxArmor.toDouble(),
        "damage" to damage.toDouble(),
        "vision" to vision.toDouble(),
        "maxSilence" to maxSilence.toDouble(),
        "maxSpeed" to maxSpeed.toDouble(),
        "maxSpeedInFocus" to maxSpeedInFocus.toDouble(),
        "heroDensity" to heroDensity.toDouble(),
        "reloadTime" to reloadTime,
        "fireRate" to fireRate,
        "fireRange" to fireRange.toDouble(),
        "fireRangeInFocus" to fireRangeInFocus.toDouble(),
        "spreadOnStay" to spreadOnStay.toDouble(),
        "spreadInMove" to spreadInMove.toDouble(),
        "spreadInFocus" to spreadInFocus.toDouble(),
        "patrons" to patrons.toDouble(),
        "powerPenetration" to powerPenetration.toDouble(),
        "damageOnHealth" to damageOnHealth,
        "damageOnArmor" to damageOnArmor,
        "armorPenetration" to armorPenetration.toDouble(),
        "focusTime" to focusTime
    )
}

fun Map<String, Double>.toHeroStats(): HeroStats {
    return HeroStats(
        this["maxHealth"]!!.toInt(),
        this["maxArmor"]!!.toInt(),
        this["damage"]!!.toInt(),
        this["vision"]!!.toInt(),
        this["maxSilence"]!!.toInt(),
        this["maxSpeed"]!!.toInt(),
        this["maxSpeedInFocus"]!!.toInt(),
        this["heroDensity"]!!.toInt(),
        this["reloadTime"]!!.toDouble(),
        this["fireRate"]!!.toDouble(),
        this["fireRange"]!!.toInt(),
        this["fireRangeInFocus"]!!.toInt(),
        this["spreadOnStay"]!!.toInt(),
        this["spreadInMove"]!!.toInt(),
        this["spreadInFocus"]!!.toInt(),
        this["patrons"]!!.toInt(),
        this["powerPenetration"]!!.toInt(),
        this["damageOnHealth"]!!.toDouble(),
        this["damageOnArmor"]!!.toDouble(),
        this["armorPenetration"]!!.toInt(),
        this["focusTime"]!!.toDouble(),
    )
}