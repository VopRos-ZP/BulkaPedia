package com.vopros.bulkapedia.hero

import com.vopros.bulkapedia.core.Entity

data class Hero(
    override val id: String,
    val active: Boolean,
    val difficult: String,
    val image: String,
    val type: String,
    val counterpicks: List<String>,
    val stats: Map<String, Double>
): Entity(id)
