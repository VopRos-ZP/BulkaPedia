package com.vopros.bulkapedia.userSet

import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.user.User

data class UserSetUseCase(
    val set: UserSet,
    val user: User,
    val hero: Hero
)
