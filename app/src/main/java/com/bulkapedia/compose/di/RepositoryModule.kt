package com.bulkapedia.compose.di

import bulkapedia.StoreRepository
import bulkapedia.categories.Category
import bulkapedia.comments.Comment
import bulkapedia.devchat.Message
import bulkapedia.effects.PersonalEffect
import bulkapedia.gears.Gear
import bulkapedia.heroInfo.HeroInfo
import bulkapedia.heroes.Hero
import bulkapedia.instances.*
import bulkapedia.mains.Main
import bulkapedia.maps.GameMap
import bulkapedia.mechanics.Mechanic
import bulkapedia.sets.UserSet
import bulkapedia.users.UserRepository
import com.bulkapedia.compose.di.annotations.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
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
    fun provideUsersRepository(
        ref: DatabaseReference,
        auth: FirebaseAuth
    ): UserRepository = UsersRepositoryImpl(ref, auth)

    /**
     * Firebase Firestore
     * **/
    @Provides
    @Singleton
    fun provideMainRepository(@MainsRef ref: CollectionReference): StoreRepository<Main> = MainsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideHeroRepository(@HeroRef ref: CollectionReference): StoreRepository<Hero> = HeroesRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideSetRepository(@SetsRef ref: CollectionReference): StoreRepository<UserSet> = SetsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideMapRepository(@MapsRef ref: CollectionReference): StoreRepository<GameMap> = MapsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideCategoryRepository(@CategoryRef ref: CollectionReference): StoreRepository<Category> = CategoryRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideCommentRepository(@CommentRef ref: CollectionReference): StoreRepository<Comment> = CommentsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideGearRepository(@GearRef ref: CollectionReference): StoreRepository<Gear> = GearsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideHeroInfoRepository(@HeroInfoRef ref: CollectionReference): StoreRepository<HeroInfo> = HeroInfoRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideMechanicRepository(@MechanicRef ref: CollectionReference): StoreRepository<Mechanic> = MechanicsRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideMessageRepository(@MessageRef ref: CollectionReference): StoreRepository<Message> = ChatRepositoryImpl(ref)

    @Provides
    @Singleton
    fun provideEffectsRepository(@EffectsRef ref: CollectionReference): StoreRepository<PersonalEffect> = EffectsRepositoryImpl(ref)

}