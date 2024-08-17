package ru.bulkapedia.data.room.categories

import ru.bulkapedia.domain.model.Category

fun CategoryDto.fromDto() = Category(
    id = id,
    title = title,
    isEnabled = isEnabled,
    isVisible = isVisible,
    route = route
)

fun Category.toDto() = CategoryDto(
    id = id,
    title = title,
    isEnabled = isEnabled,
    isVisible = isVisible,
    route = route
)
