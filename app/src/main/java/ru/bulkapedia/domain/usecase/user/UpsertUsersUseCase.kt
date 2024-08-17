package ru.bulkapedia.domain.usecase.user

import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.UserRepository

class UpsertUsersUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(users: List<User>) {
        users.forEach { userRepository.upsert(it) }
    }

}