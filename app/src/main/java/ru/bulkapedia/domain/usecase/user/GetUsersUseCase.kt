package ru.bulkapedia.domain.usecase.user

import ru.bulkapedia.domain.repository.UserRepository
import ru.bulkapedia.domain.usecase.BaseUseCase

class GetUsersUseCase(
    private val userRepository: UserRepository
) : BaseUseCase() {

    operator fun invoke() = userRepository.listenAll().stateIn()

    suspend fun fetchAll() = userRepository.fetchAll()

}