package com.bulkapedia.compose.di

import com.bulkapedia.data.Repository
import com.bulkapedia.data.categories.Category
import com.bulkapedia.data.comments.Comment
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.hero_info.HeroInfo
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.mains.Main
import com.bulkapedia.data.maps.Map
import com.bulkapedia.data.mechanics.Mechanic
import com.bulkapedia.data.messages.Message
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.data.users.UsersRepository
import com.bulkapedia.data.users.UsersRepositoryImpl
import com.bulkapedia.domain.categories.CategoryRepository
import com.bulkapedia.domain.categories.CategoryRepositoryImpl
import com.bulkapedia.domain.comments.CommentRepository
import com.bulkapedia.domain.comments.CommentRepositoryImpl
import com.bulkapedia.domain.gears.GearRepository
import com.bulkapedia.domain.gears.GearRepositoryImpl
import com.bulkapedia.domain.hero_info.HeroInfoRepository
import com.bulkapedia.domain.hero_info.HeroInfoRepositoryImpl
import com.bulkapedia.domain.heroes.HeroRepository
import com.bulkapedia.domain.heroes.HeroRepositoryImpl
import com.bulkapedia.domain.mains.MainRepository
import com.bulkapedia.domain.mains.MainRepositoryImpl
import com.bulkapedia.domain.maps.MapRepository
import com.bulkapedia.domain.maps.MapRepositoryImpl
import com.bulkapedia.domain.mechanics.MechanicRepository
import com.bulkapedia.domain.mechanics.MechanicRepositoryImpl
import com.bulkapedia.domain.messages.MessageRepository
import com.bulkapedia.domain.messages.MessageRepositoryImpl
import com.bulkapedia.domain.sets.UserSetRepository
import com.bulkapedia.domain.sets.UserSetRepositoryImpl
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
    fun provideMainRepository(): Repository<Main> = MainRepositoryImpl()

    @Provides
    @Singleton
    fun provideHeroRepository(): Repository<Hero> = HeroRepositoryImpl()

    @Provides
    @Singleton
    fun provideSetRepository(): Repository<UserSet> = UserSetRepositoryImpl()

    @Provides
    @Singleton
    fun provideMapRepository(): Repository<Map> = MapRepositoryImpl()

    @Provides
    @Singleton
    fun provideCategoryRepository(): Repository<Category> = CategoryRepositoryImpl()

    @Provides
    @Singleton
    fun provideCommentRepository(): Repository<Comment> = CommentRepositoryImpl()

    @Provides
    @Singleton
    fun provideGearRepository(): Repository<Gear> = GearRepositoryImpl()

    @Provides
    @Singleton
    fun provideHeroInfoRepository(): Repository<HeroInfo> = HeroInfoRepositoryImpl()

    @Provides
    @Singleton
    fun provideMechanicRepository(): Repository<Mechanic> = MechanicRepositoryImpl()

    @Provides
    @Singleton
    fun provideMessageRepository(): Repository<Message> = MessageRepositoryImpl()

}