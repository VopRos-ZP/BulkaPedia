package ru.bulkapedia.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import ru.bulkapedia.domain.utils.ResourceManager
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.screens.gear_prices.GearPricesScreen
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices
import ru.bulkapedia.presentation.ui.screens.root.RootContent
import ru.bulkapedia.presentation.ui.theme.BulkaPediaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resourceManager = ResourceManager()



        setContent {
            KoinAndroidContext {
                BulkaPediaTheme {
                    GearPricesScreen(
                        koinInject(
                            parameters = GearPrices.params(
                                onBackClick = {},
                                context = koinInject(
                                    parameters = { parametersOf(defaultComponentContext()) }
                                )
                            )
                        )
                    )
                }
            }
        }
    }

}
