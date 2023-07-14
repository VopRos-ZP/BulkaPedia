package bulkapedia.users

data class UserDTO(
    var email: String = "",
    var password: String = "",
    var nickname: String = "",
    var updateEmail: String = "",
    var updateNickname: String = ""
)