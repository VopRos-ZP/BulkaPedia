package ru.bulkapedia.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.presentation.ui.screens.gear_prices.component.DefaultGearPricesComponent
import ru.bulkapedia.presentation.ui.screens.gear_prices.component.GearPricesComponent
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPricesStoreFactory

private val storeModule = module {
    singleOf(::DefaultStoreFactory) { bind<StoreFactory>() }

    factoryOf(::GearPricesStoreFactory)
}

private val componentModule = module {
    singleOf(::DefaultGearPricesComponent) { bind<GearPricesComponent>() }
}

internal val presentationModule = module {
    includes(
        storeModule,
        componentModule,
    )
}