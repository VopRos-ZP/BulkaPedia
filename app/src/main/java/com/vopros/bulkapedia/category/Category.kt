package com.vopros.bulkapedia.category

import com.vopros.bulkapedia.core.Entity

data class Category(
    override val id: String,
    val title: String,
    val subTitle: String,
    val enabled: Boolean,
    val icon: String,
    val destination: String,
): Entity(id)