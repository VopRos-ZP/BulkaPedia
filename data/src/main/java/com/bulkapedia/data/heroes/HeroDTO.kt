package com.bulkapedia.data.heroes

data class HeroDTO(
    var name: String = "",
    var icon: String = "",
    var type: String = "",
    var counterpicks: List<String> = emptyList(),
    var personalGears: Map<String, String> = emptyMap(),
    var stats: Map<String, Double> = emptyMap(),
    var difficult: String = ""
)
