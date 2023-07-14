package bulkapedia.users

data class User(
    val userId: String,
    val email: String,
    val password: String,
    val nickname: String,
    val updateEmail: String,
    val updateNickname: String
)
