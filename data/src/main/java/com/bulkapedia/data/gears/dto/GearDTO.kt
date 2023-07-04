package com.bulkapedia.data.gears.dto

data class GearDTO(
    var icon: String = "",
    var gearCell: String = "",
    var gearSet: String = "",
    var effects: Map<String, EffectDTO> = emptyMap(),
    var ranks: Map<String, List<Int>> = emptyMap()
)