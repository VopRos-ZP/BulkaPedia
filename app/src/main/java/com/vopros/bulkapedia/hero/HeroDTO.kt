package com.vopros.bulkapedia.hero

import com.google.firebase.firestore.DocumentId

data class HeroDTO(
    @DocumentId var id: String = "",
    var active: Boolean = false,
    var difficult: String = "easy",
    var image: String = "",
    var type: String = "",
    var counterpicks: List<String> = emptyList(),
    var stats: Map<String, Double> = emptyMap()
)