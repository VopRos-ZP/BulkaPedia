package vopros.bulkapedia.di

import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.firebase.AuthRepositoryImpl
import vopros.bulkapedia.user.UserRepository
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