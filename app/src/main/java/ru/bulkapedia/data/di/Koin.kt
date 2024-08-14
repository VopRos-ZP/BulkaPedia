package ru.bulkapedia.data.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.category.GetAllCategoryUseCase
import ru.bulkapedia.domain.usecase.*
import ru.bulkapedia.data.repository.hero.HeroRepositoryImpl
import ru.bulkapedia.data.repository.fraction.FractionRepositoryImpl
import ru.bulkapedia.domain.repository.FractionRepository
import ru.bulkapedia.domain.repository.HeroRepository
import ru.bulkapedia.presentation.screens.categories.CategoriesViewModel
import ru.bulkapedia.presentation.screens.login.LoginViewModel
import ru.bulkapedia.presentation.screens.registration.RegistrationViewModel
import ru.bulkapedia.presentation.screens.sets.SetsViewModel

val supabaseModule = module {
    singleOf(::SupabaseWrapper)

    /** Repositories **/
    singleOf(::HeroRepositoryImpl) { bind<HeroRepository>() }
    singleOf(::FractionRepositoryImpl) { bind<FractionRepository>() }

    /** UseCases **/
    singleOf(::GetAllCategoryUseCase)
    singleOf(::LoginUseCase)
    singleOf(::GetHeroesUseCase)
    singleOf(::GetFractionsUseCase)
    singleOf(::RegistrationUseCase)

    /** ViewModels **/
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::SetsViewModel)
}
