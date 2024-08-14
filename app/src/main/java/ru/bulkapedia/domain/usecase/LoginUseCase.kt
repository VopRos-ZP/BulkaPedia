package ru.bulkapedia.domain.usecase

import io.github.jan.supabase.gotrue.providers.builtin.Email
import ru.bulkapedia.data.SupabaseWrapper

class LoginUseCase(
    private val wrapper: SupabaseWrapper
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ) = try {
        wrapper.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
        true
    }  catch (_: Exception) {
        false
    }

}