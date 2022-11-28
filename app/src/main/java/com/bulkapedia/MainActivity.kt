package com.bulkapedia

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.ActivityMainBinding
import com.bulkapedia.preference.UserPreferences
import com.bulkapedia.utils.BottomMenuUtils
import com.bulkapedia.utils.Language
import com.google.firebase.firestore.DocumentChange

class MainActivity : AppCompatActivity() {

    lateinit var prefs: UserPreferences
    lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController

    private val channelId: String = "ADMIN_CHANNEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Инициализация root элемента и bind
        super.onCreate(savedInstanceState)

        MAIN = this
        createNotificationChannelId()
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Инициализация всех элеменотов
        bind.bottomNav.setupWithNavController(navController)
        // Инициализируем Preferences
        initPreferences()
        // Обновляем Views
        updateViews()
        // init Notification
        Database().getAllUsers { users ->
            for (user in users) {
                if (user.nickname == ADMIN_NICKNAME) continue
                Database().getChatNode()
                    .addSnapshotListener { value, _ ->
                        value?.query?.whereEqualTo("author", user.nickname)?.get()
                            ?.addOnSuccessListener { q ->
                                q.documentChanges.forEach { docChange ->
                                    if (docChange.type == DocumentChange.Type.ADDED) {
                                        val chatModel = Database().getChatInfoBySnapshot(docChange.document)
                                        if (prefs.getSigned() && prefs.getEmail() == ADMIN_EMAIL && !chatModel.read) {
                                            val notification = Notification.Builder(this, channelId)
                                                    .setSmallIcon(R.mipmap.ic_launcher)
                                                    .setContentTitle(chatModel.author)
                                                    .setContentText(chatModel.text)
                                                    .setShowWhen(true)
                                                    .build()
                                            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                                            manager.notify(docChange.newIndex, notification)
                                            // update read
                                            Database().getChatNode().document(docChange.document.id)
                                                .update("read", true)
                                        }
                                    }
                                }
                            }
                    }
            }
        }
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

    private fun createNotificationChannelId() {
        val name = "admin_channel"
        val descriptionText = "Channel for admin"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}