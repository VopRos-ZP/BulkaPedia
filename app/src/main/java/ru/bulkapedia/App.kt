package ru.bulkapedia

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import ru.bulkapedia.data.di.appModule
import ru.bulkapedia.data.di.supabaseModule

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            workManagerFactory()

            modules(appModule)
        }
    }

}