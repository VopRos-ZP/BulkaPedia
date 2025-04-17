package ru.bulkapedia.di

import org.koin.dsl.module

private val useCaseModule = module {

}

internal val domainModule = module {
    includes(
        useCaseModule
    )
}