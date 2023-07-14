package bulkapedia.users

fun User.toDTO() = UserDTO(email, password, nickname, updateEmail, updateNickname)

fun UserDTO.toPOJO() = User("", email, password, nickname, updateEmail, updateNickname)