package ru.bulkapedia.data.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.usecase.category.GetAllCategoryUseCase
import ru.bulkapedia.domain.usecase.category.UpsertCategoryUseCase
import ru.bulkapedia.domain.usecase.*
import ru.bulkapedia.domain.usecase.user.*
import ru.bulkapedia.data.repository.hero.HeroRepositoryImpl
import ru.bulkapedia.data.repository.fraction.FractionRepositoryImpl
import ru.bulkapedia.data.repository.UserRepositoryImpl
import ru.bulkapedia.data.repository.CategoryRepositoryImpl
import ru.bulkapedia.domain.repository.FractionRepository
import ru.bulkapedia.domain.repository.HeroRepository
import ru.bulkapedia.presentation.screens.categories.CategoriesViewModel
import ru.bulkapedia.presentation.screens.login.LoginViewModel
import ru.bulkapedia.presentation.screens.registration.RegistrationViewModel
import ru.bulkapedia.presentation.screens.sets.SetsViewModel
import ru.bulkapedia.data.room.categories.CategoryDatabase
import ru.bulkapedia.data.room.heroes.HeroDatabase
import ru.bulkapedia.data.room.user.UserDatabase
import ru.bulkapedia.data.workmanager.*
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.domain.repository.UserRepository

val supabaseModule = module {
    singleOf(::SupabaseWrapper)
}

val roomModule = module {
    /** Databases **/
    single { Room.databaseBuilder(get(), CategoryDatabase::class.java, CategoryDatabase.NAME).fallbackToDestructiveMigration().build() }
    single { Room.databaseBuilder(get(), UserDatabase::class.java, UserDatabase.NAME).fallbackToDestructiveMigration().build() }
    single { Room.databaseBuilder(get(), HeroDatabase::class.java, HeroDatabase.NAME).fallbackToDestructiveMigration().build() }
}

val repositoryModule = module {
    /** Repositories **/
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::HeroRepositoryImpl) { bind<HeroRepository>() }
    singleOf(::FractionRepositoryImpl) { bind<FractionRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}

val useCaseModule = module {
    /** UseCases **/
    // Category
    singleOf(::GetAllCategoryUseCase)
    singleOf(::UpsertCategoryUseCase)
    // Fraction
    singleOf(::UpsertFractionUseCase)
    // Heroes
    singleOf(::GetHeroesUseCase)
    singleOf(::UpsertHeroesUseCase)
    // Users
    singleOf(::GetUsersUseCase)
    singleOf(::UpsertUsersUseCase)

    // Login / Reg
    singleOf(::LoginUseCase)
    singleOf(::RegistrationUseCase)
}

val workManagerModule = module {
   workerOf(::CategoriesWorker)
   workerOf(::HeroesWorker)
   workerOf(::FractionsWorker)
   workerOf(::UsersWorker)
}

val viewModelModule = module {
    /** ViewModels **/
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::SetsViewModel)
}

val dataModule = module {
    includes(
        supabaseModule,
        roomModule,
        workManagerModule
    )
}

val domainModule = module {
    includes(
        repositoryModule,
        useCaseModule
    )
}

val appModule = module {
    includes(
        dataModule,
        domainModule,
        viewModelModule
    )
}