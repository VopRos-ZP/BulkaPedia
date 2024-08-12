package ru.bulkapedia.domain.users

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import ru.bulkapedia.data.SupabaseWrapper

class GetAllUsersUseCase(private val wrapper: SupabaseWrapper) {

    @OptIn(SupabaseExperimental::class)
    operator fun invoke() = wrapper.postgres.from(table = "users").selectAsFlow(User::id)

}