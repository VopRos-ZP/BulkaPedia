package ru.bulkapedia.domain.usecase.user

import ru.bulkapedia.domain.repository.UserRepository
import ru.bulkapedia.domain.usecase.BaseUseCase

class GetUsersUseCase(
    private val userRepository: UserRepository
) : BaseUseCase() {

    suspend operator fun invoke() = userRepository.fetchAll()

    val users = userRepository.users.stateIn()

}