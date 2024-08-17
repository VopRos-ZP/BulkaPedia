package ru.bulkapedia.data.room.user

import ru.bulkapedia.domain.model.User

fun UserDto.fromDto() = User(
    id = id,
    nickname = nickname,
    email = email,
    password = password,
    role = role
)

fun User.toDto() = UserDto(
    id = id,
    nickname = nickname,
    email = email,
    password = password,
    role = role
)
