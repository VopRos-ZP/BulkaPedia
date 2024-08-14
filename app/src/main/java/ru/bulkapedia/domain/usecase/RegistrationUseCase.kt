package ru.bulkapedia.domain.usecase

import io.github.jan.supabase.gotrue.providers.builtin.Email
import ru.bulkapedia.R
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.model.User

class RegistrationUseCase(
    private val wrapper: SupabaseWrapper
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        nickname: String,
    ): Int {
        if (containsEmailInDb(email)) {
            return R.string.error_registration // Email exists in db
        }
        return wrapper.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }?.let {
            wrapper.postgres.from("users").insert(
                User(
                    id = it.id,
                    email = email,
                    password = password,
                    nickname = nickname,
                    role = "user"
                )
            )
            R.string.ok
        } ?: R.string.error_registration_title
    }

    private suspend fun containsEmailInDb(email: String): Boolean {
        return wrapper.postgres.from("users").select {
            filter { User::email eq email }
        }.decodeSingleOrNull<User>() != null
    }

}