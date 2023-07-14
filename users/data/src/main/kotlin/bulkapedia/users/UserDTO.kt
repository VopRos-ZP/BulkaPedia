package bulkapedia.users

import com.google.firebase.database.PropertyName

data class UserDTO(
    @PropertyName("email") var email: String = "",
    @PropertyName("password") var password: String = "",
    @PropertyName("nickname") var nickname: String = "",
    @PropertyName("updateEmail") var updateEmail: String = "",
    @PropertyName("updateNickname") var updateNickname: String = ""
)