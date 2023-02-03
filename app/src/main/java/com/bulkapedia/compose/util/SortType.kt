package com.bulkapedia.compose.util

sealed class SortType<T : Enum<T>> {
    object None: SortType<NoneType>()
    data class ByHero(var type: HeroType) : SortType<HeroType>()
    data class ByMap(val type: MapType): SortType<MapType>()
}

enum class NoneType { NONE; }

enum class HeroType(private val ruName: String) {
    SHORTGUNS("Дробовики"),
    SCOUTS("Разведчики"),
    SNIPERS("Снайперы"),
    TANKS("Танки"),
    TROOPERS("Штурмовики");
    fun str(): String = name.lowercase()
    fun strRu(): String = ruName
}

enum class MapType(private val ruName: String) {
    BATTLE_ROYALE("Королевская битва"),
    KING_OF_THE_HILL("Царь горы"),
    SQUAD("Стенка"),
    SABOTAGE("Саботаж");
    fun str(): String = name.lowercase()
    fun strRu(): String = ruName
}
