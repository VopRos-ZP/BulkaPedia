package com.bulkapedia

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bulkapedia.databinding.ActivityMainBinding
import com.bulkapedia.preference.UserPreferences
import com.bulkapedia.utils.BottomMenuUtils
import com.bulkapedia.utils.Language

class MainActivity : AppCompatActivity() {

    lateinit var prefs: UserPreferences
    lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        // Инициализация root элемента и bind
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        MAIN = this
        // Инициализация всех элеменотов
        bind.bottomNav.setupWithNavController(navController)
        // Инициализируем Preferences
        initPreferences()
        // Обновляем Views
        updateViews()
    }

    fun updateViews() {
        bind.root.allViews.iterator().forEach(View::invalidate)
        bind.bottomNav.menu.forEach {
            it.title = getString(BottomMenuUtils.mapMenuItems[it.itemId]!!)
        }
    }

    private fun initPreferences() {
        prefs = application as UserPreferences
        val shared = getPreferences()
        val lang = shared.getString(UserPreferences.LANGUAGE, UserPreferences.EN_LANGUAGE)
        if (lang != null) {
            prefs.setLanguage(lang)
            Language.update(baseContext, prefs.getLanguage())
        }
        // User
        val signed = shared.getBoolean(UserPreferences.SIGNED, false)
        val login = shared.getString(UserPreferences.EMAIL, "")
        val password = shared.getString(UserPreferences.PASSWORD, "")
        val nickname = shared.getString(UserPreferences.NICKNAME, "")
        prefs.setSigned(signed)
        if (signed) {
            bind.bottomNav.menu[2].apply {
                setIcon(R.drawable.person)
                setTitle(R.string.profile)
            }
        }
        if (login != null) prefs.setEmail(login)
        if (password != null) prefs.setPassword(password)
        if (nickname != null) prefs.setNickname(nickname)
    }

    fun getPreferences() : SharedPreferences {
        return getSharedPreferences(UserPreferences.PREFERENCES, MODE_PRIVATE)
    }

}