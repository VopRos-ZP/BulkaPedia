package bulkapedia.categories.utils

import bulkapedia.categories.Category
import bulkapedia.categories.CategoryDTO

fun Category.toDTO(): CategoryDTO = CategoryDTO(categoryId, destination, enabled, icon, title, subTitle)

fun CategoryDTO.toPOJO(): Category = Category(categoryId, destination, enabled, icon, title, subTitle)