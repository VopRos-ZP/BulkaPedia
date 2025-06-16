package ru.bulkapedia.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.bulkapedia.presentation.ui.screens.categories.CategoriesViewModel
import ru.bulkapedia.presentation.ui.screens.heroDetails.HeroDetailsViewModel
import ru.bulkapedia.presentation.ui.screens.heroes.HeroesViewModel
import ru.bulkapedia.presentation.ui.screens.splash.SplashViewModel

private val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::HeroesViewModel)
    viewModelOf(::HeroDetailsViewModel)
}

internal val presentationModule = module {
    includes(
        viewModelModule,
    )
}