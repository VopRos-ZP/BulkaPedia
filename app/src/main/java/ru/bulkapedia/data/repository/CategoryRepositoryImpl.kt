package ru.bulkapedia.data.repository

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.models.category.CategoryDto
import ru.bulkapedia.data.models.category.fromDto
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.domain.utils.Table

class CategoryRepositoryImpl(
    supabaseHelper: SupabaseHelper
) : CategoryRepository {

    @OptIn(SupabaseExperimental::class)
    override val categories: Flow<List<Category>> = supabaseHelper.postgrest
        .from(Table.CATEGORIES)
        .selectAsFlow(
            primaryKey = CategoryDto::id,
            filter = FilterOperation(
                column = "is_visible",
                operator = FilterOperator.EQ,
                value = true
            )
        )
        .map { it.map(CategoryDto::fromDto) }

}