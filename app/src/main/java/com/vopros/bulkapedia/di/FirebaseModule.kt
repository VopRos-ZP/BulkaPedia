package com.vopros.bulkapedia.di

import com.vopros.bulkapedia.firebase.AuthRepository
import com.vopros.bulkapedia.firebase.AuthRepositoryImpl
import com.vopros.bulkapedia.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideAuthRepository(userRepository: UserRepository): AuthRepository = AuthRepositoryImpl(userRepository)

}