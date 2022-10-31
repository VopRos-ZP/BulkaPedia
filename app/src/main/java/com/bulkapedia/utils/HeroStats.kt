package com.bulkapedia.utils

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