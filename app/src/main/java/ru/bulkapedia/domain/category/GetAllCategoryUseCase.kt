package ru.bulkapedia.domain.category

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import ru.bulkapedia.data.SupabaseWrapper

class GetAllCategoryUseCase(
    private val wrapper: SupabaseWrapper
) {

    @OptIn(SupabaseExperimental::class)
    operator fun invoke() = wrapper.postgres.from("categories").selectAsFlow(Category::id)

}