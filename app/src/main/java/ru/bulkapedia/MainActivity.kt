package ru.bulkapedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import ru.bulkapedia.data.workmanager.CategoriesWorker
import ru.bulkapedia.data.workmanager.FractionsWorker
import ru.bulkapedia.data.workmanager.HeroesWorker
import ru.bulkapedia.presentation.screens.main.MainScreen

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoriesWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<CategoriesWorker>()
                .build()

        val heroesWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<HeroesWorker>()
                .build()

        val fractionsWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<FractionsWorker>()
                .build()

        WorkManager.getInstance(this).enqueue(categoriesWorkRequest)
        WorkManager.getInstance(this).enqueue(heroesWorkRequest)
        WorkManager.getInstance(this).enqueue(fractionsWorkRequest)

        setContent { KoinAndroidContext { MainScreen() } }
    }

}
