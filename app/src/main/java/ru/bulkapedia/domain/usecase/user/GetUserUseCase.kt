package ru.bulkapedia.domain.usecase.user

import ru.bulkapedia.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String) = userRepository.fetchByEmail(email)

}