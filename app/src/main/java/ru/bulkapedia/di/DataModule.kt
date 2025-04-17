package ru.bulkapedia.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bulkapedia.data.repository.GearPriceRepositoryImpl
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.repository.GearPriceRepository

private val networkModule = module {
    singleOf(::SupabaseHelper)
}

private val roomModule = module {

}

private val repositoryModule = module {
    singleOf(::GearPriceRepositoryImpl) { bind<GearPriceRepository>() }
}

internal val dataModule = module {
    includes(
        networkModule,
        roomModule,
        repositoryModule,
    )
}