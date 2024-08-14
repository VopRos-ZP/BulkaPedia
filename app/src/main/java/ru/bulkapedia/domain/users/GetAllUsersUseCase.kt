package ru.bulkapedia.domain.users

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.model.User

class GetAllUsersUseCase(private val wrapper: SupabaseWrapper) {

    @OptIn(SupabaseExperimental::class)
    operator fun invoke() = wrapper.postgres.from(table = "users")
        .selectAsFlow(User::id)
        .distinctUntilChanged()

}