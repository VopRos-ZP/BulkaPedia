package com.bulkapedia.compose.di

import com.bulkapedia.compose.data.repos.categories.CategoriesRepository
import com.bulkapedia.compose.data.repos.categories.CategoriesRepositoryImpl
import com.bulkapedia.compose.data.repos.comments.CommentsRepository
import com.bulkapedia.compose.data.repos.comments.CommentsRepositoryImpl
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.database.UsersRepositoryImpl
import com.bulkapedia.compose.data.repos.gears.GearsRepository
import com.bulkapedia.compose.data.repos.gears.GearsRepositoryImpl
import com.bulkapedia.compose.data.repos.hero_info.HeroInfoRepository
import com.bulkapedia.compose.data.repos.hero_info.HeroIngoRepositoryImpl
import com.bulkapedia.compose.data.repos.heroes.HeroesRepository
import com.bulkapedia.compose.data.repos.heroes.HeroesRepositoryImpl
import com.bulkapedia.compose.data.repos.maps.MapsRepository
import com.bulkapedia.compose.data.repos.maps.MapsRepositoryImpl
import com.bulkapedia.compose.data.repos.mechanics.MechanicsRepository
import com.bulkapedia.compose.data.repos.mechanics.MechanicsRepositoryImpl
import com.bulkapedia.compose.data.repos.messages.MessagesRepository
import com.bulkapedia.compose.data.repos.messages.MessagesRepositoryImpl
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepositoryImpl
import com.bulkapedia.compose.data.repos.stats.StatsRepository
import com.bulkapedia.compose.data.repos.stats.StatsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * Firebase Database
     * **/
    @Provides
    @Singleton
    fun provideUsersRepository(): UsersRepository = UsersRepositoryImpl()

    /**
     * Firebase Firestore
     * **/
    @Provides
    @Singleton
    fun provideStatsRepository(): StatsRepository = StatsRepositoryImpl()

    @Provides
    @Singleton
    fun provideHeroesRepository(): HeroesRepository = HeroesRepositoryImpl()

    @Provides
    @Singleton
    fun provideSetsRepository(): SetsRepository = SetsRepositoryImpl()

    @Provides
    @Singleton
    fun provideMapsRepository(): MapsRepository = MapsRepositoryImpl()

    @Provides
    @Singleton
    fun provideCategoriesRepository(): CategoriesRepository = CategoriesRepositoryImpl()

    @Provides
    @Singleton
    fun provideCommentsRepository(): CommentsRepository = CommentsRepositoryImpl()

    @Provides
    @Singleton
    fun provideGearsRepository(): GearsRepository = GearsRepositoryImpl()

    @Provides
    @Singleton
    fun provideHeroInfoRepository(): HeroInfoRepository = HeroIngoRepositoryImpl()

    @Provides
    @Singleton
    fun provideMechanicsRepository(): MechanicsRepository = MechanicsRepositoryImpl()

    @Provides
    @Singleton
    fun provideMessagesRepository(): MessagesRepository = MessagesRepositoryImpl()

}