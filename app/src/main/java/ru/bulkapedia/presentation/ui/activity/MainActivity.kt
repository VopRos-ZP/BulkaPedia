package ru.bulkapedia.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.bulkapedia.domain.utils.ResourceManager
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.screens.root.RootContent
import ru.bulkapedia.presentation.ui.theme.BulkaPediaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resourceManager = ResourceManager()

        setContent {
            BulkaPediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootContent()
                }
            }
        }
    }

}
