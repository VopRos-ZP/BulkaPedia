package vopros.bulkapedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import vopros.bulkapedia.ui.components.Home
import vopros.bulkapedia.ui.theme.BulkapediaTheme
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel
import vopros.bulkapedia.utils.ResourceManager
import vopros.bulkapedia.utils.resourceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resourceManager = ResourceManager()
        setContent { BulkapediaTheme { Home() } }
    }

}