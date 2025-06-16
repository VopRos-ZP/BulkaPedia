package ru.bulkapedia.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.data.models.hero.HeroesRepositoryImpl
import ru.bulkapedia.data.repository.CategoryRepositoryImpl
import ru.bulkapedia.data.repository.GearPriceRepositoryImpl
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.domain.repository.GearPriceRepository
import ru.bulkapedia.domain.repository.HeroesRepository

private val networkModule = module {
    singleOf(::SupabaseHelper)
}

private val roomModule = module {

}

private val repositoryModule = module {
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::HeroesRepositoryImpl) { bind<HeroesRepository>() }
    singleOf(::GearPriceRepositoryImpl) { bind<GearPriceRepository>() }
}

internal val dataModule = module {
    includes(
        networkModule,
        roomModule,
        repositoryModule,
    )
}