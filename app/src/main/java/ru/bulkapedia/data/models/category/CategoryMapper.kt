package ru.bulkapedia.data.models.category

import ru.bulkapedia.domain.model.Category

fun CategoryDto.toCategory() = Category(
    id = id, title = title,
    enabled = enabled,
    visible = visible
)

fun Category.toDto() = CategoryDto(
    id = id, title = title,
    enabled = enabled,
    visible = visible
)