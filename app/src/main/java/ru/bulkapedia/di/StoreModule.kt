package ru.bulkapedia.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.bulkapedia.di.qualifiers.DefaultSF
import ru.bulkapedia.di.qualifiers.LoggingSF
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.presentation.screens.signIn.mvi.SignInStore
import ru.bulkapedia.presentation.screens.signIn.mvi.SignInStoreImpl
import ru.bulkapedia.presentation.ui.screens.categories.mvi.CategoriesStoreFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StoreModule {

    companion object {

        @Provides
        @Singleton
        @DefaultSF
        fun provideDefaultStoreFactory(): StoreFactory = DefaultStoreFactory()

        @Provides
        @Singleton
        @LoggingSF
        fun provideLoggingStoreFactory(
            @DefaultSF storeFactory: StoreFactory
        ): StoreFactory = LoggingStoreFactory(storeFactory)

        @Provides
        @Singleton
        fun provideCategoriesStoreFactory(
            @DefaultSF storeFactory: StoreFactory,
            categoryRepository: CategoryRepository
        ): CategoriesStoreFactory {
            return CategoriesStoreFactory(storeFactory, categoryRepository)
        }

    }

    @Binds
    @Singleton
    fun bindSignInStore(signInStoreImpl: SignInStoreImpl): SignInStore

}