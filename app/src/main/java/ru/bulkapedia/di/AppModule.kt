package ru.bulkapedia.di

import org.koin.dsl.module

internal val appModule = module {
    includes(
        dataModule,
        presentationModule
    )
}