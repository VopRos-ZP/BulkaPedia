package vopros.bulkapedia.userSet

import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.user.User

data class UserSetUseCase(
    val set: UserSet,
    val user: User,
    val hero: Hero
)
