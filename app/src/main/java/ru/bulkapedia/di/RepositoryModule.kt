package ru.bulkapedia.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.bulkapedia.data.models.comment.CommentsRepositoryImpl
import ru.bulkapedia.data.models.map.MapsRepositoryImpl
import ru.bulkapedia.data.repository.AuthRepositoryImpl
import ru.bulkapedia.data.repository.UserRepositoryImpl
import ru.bulkapedia.domain.repository.AuthRepository
import ru.bulkapedia.domain.repository.CommentsRepository
import ru.bulkapedia.domain.repository.MapsRepository
import ru.bulkapedia.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindCommentsRepository(commentsRepositoryImpl: CommentsRepositoryImpl): CommentsRepository

    @Binds
    @Singleton
    fun bindMapsRepository(mapsRepositoryImpl: MapsRepositoryImpl): MapsRepository

}