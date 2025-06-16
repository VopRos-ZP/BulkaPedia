package ru.bulkapedia.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.compose.KoinAndroidContext
import ru.bulkapedia.domain.utils.ResourceManager
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.screens.root.RootScreen
import ru.bulkapedia.presentation.ui.theme.BulkaPediaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resourceManager = ResourceManager()

        setContent {
            KoinAndroidContext {
                BulkaPediaTheme {
                    RootScreen()
                }
            }
        }
    }

}
