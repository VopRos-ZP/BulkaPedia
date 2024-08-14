package ru.bulkapedia.domain.category

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.model.Category

class GetAllCategoryUseCase(
    private val wrapper: SupabaseWrapper
) {

    @OptIn(SupabaseExperimental::class)
    operator fun invoke() = wrapper.postgres.from("categories")
        .selectAsFlow(Category::id)
        .distinctUntilChanged()

}