package com.bulkapedia.data.categories

data class CategoryDTO(
    var title: String = "",
    var subTitle: String = "",
    var enabled: Boolean = false,
    var destination: String = "",
    var icon: String = ""
)