package com.bulkapedia.preference

import android.app.Application
import com.bulkapedia.database.User

class UserPreferences : Application() {

    companion object {
        const val PREFERENCES = "preferences"
        const val LANGUAGE = "language"
        const val RU_LANGUAGE = "ru"
        const val EN_LANGUAGE = "en"
        const val SIGNED = "signed"
        const val ID = "id"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val NICKNAME = "nickname"
    }

    private lateinit var language: String
    private var signed: Boolean = false
    private var email: String? = null
    private var password: String? = null
    private var nickname: String? = null

    fun setLanguage(lang: String) {
        language = lang
    }

    fun getLanguage() : String = language


    fun setEmail(email: String?) {
        this.email = email
    }

    fun getEmail(): String? = email

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getPassword(): String? = password

    fun setNickname(nickname: String?) {
        this.nickname = nickname
    }

    fun getNickname(): String? = nickname

    fun setSigned(signed: Boolean) {
        this.signed = signed
    }

    fun getSigned(): Boolean = signed

    fun setUser(user: User) {
        setEmail(user.email)
        setPassword(user.password)
        setNickname(user.nickname)
    }

    fun getUser(): User = User(email, password, nickname)

}