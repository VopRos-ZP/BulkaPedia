package com.bulkapedia.compose.provides

import com.vopros.data.category.CategoryRepositoryImpl
import com.vopros.data.comment.CommentRepositoryImpl
import com.vopros.data.gear.GearRepositoryImpl
import com.vopros.data.hero.HeroRepositoryImpl
import com.vopros.data.heroinfo.HeroInfoRepositoryImpl
import com.vopros.data.map.MapRepositoryImpl
import com.vopros.data.mechanic.MechanicRepositoryImpl
import com.vopros.data.message.MessageRepositoryImpl
import com.vopros.data.set.SetRepositoryImpl
import com.vopros.data.user.UserRepositoryImpl
import com.vopros.domain.Repository
import com.vopros.domain.categories.Category
import com.vopros.domain.comment.Comment
import com.vopros.domain.gear.Gear
import com.vopros.domain.hero.Hero
import com.vopros.domain.heroinfo.HeroInfo
import com.vopros.domain.map.Map
import com.vopros.domain.mechanics.Mechanic
import com.vopros.domain.message.Message
import com.vopros.domain.set.Set
import com.vopros.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(): UserRepository = UserRepositoryImpl()

    @Singleton
    @Provides
    fun providesMapsRepository(): Repository<Map> = MapRepositoryImpl()

    @Singleton
    @Provides
    fun providesSetRepository(): Repository<Set> = SetRepositoryImpl()

    @Singleton
    @Provides
    fun providesCommentRepository(): Repository<Comment> = CommentRepositoryImpl()

    @Singleton
    @Provides
    fun providesHeroRepository(): Repository<Hero> = HeroRepositoryImpl()

    @Singleton
    @Provides
    fun providesGearRepository(): Repository<Gear> = GearRepositoryImpl()

    @Singleton
    @Provides
    fun providesMechanicRepository(): Repository<Mechanic> = MechanicRepositoryImpl()

    @Singleton
    @Provides
    fun providesCategoryRepository(): Repository<Category> = CategoryRepositoryImpl()

    @Singleton
    @Provides
    fun providesHeroInfoRepository(): Repository<HeroInfo> = HeroInfoRepositoryImpl()

    @Singleton
    @Provides
    fun providesMessageRepository(): Repository<Message> = MessageRepositoryImpl()

}