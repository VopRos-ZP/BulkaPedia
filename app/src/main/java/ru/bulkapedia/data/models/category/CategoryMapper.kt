package ru.bulkapedia.data.models.category

import ru.bulkapedia.domain.model.Category

fun CategoryDto.fromDto() = Category(
    id = id,
    name = name,
    route = route,
    isEnable = isEnable,
    isVisible = isVisible,
)

fun Category.toDto() = CategoryDto(
    id = id,
    name = name,
    route = route,
    isEnable = isEnable,
    isVisible = isVisible,
)