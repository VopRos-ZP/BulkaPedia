package com.bulkapedia.compose.di

import com.bulkapedia.compose.di.annotations.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    fun provideAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(): DatabaseReference = Firebase.database.reference.child("users")

    @Provides
    @MainsRef
    fun provideMainsRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("mains")

    @Provides
    @MapsRef
    fun provideMapsRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("maps")

    @Provides
    @HeroRef
    fun provideHeroRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("heroes")

    @Provides
    @HeroInfoRef
    fun provideHeroInfoRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("heroInfo")

    @Provides
    @CategoryRef
    fun provideCategoryRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("categories")

    @Provides
    @CommentRef
    fun provideCommentRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("comments")

    @Provides
    @GearRef
    fun provideGearRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("gears")

    @Provides
    @MechanicRef
    fun provideMechanicRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("mechanics")

    @Provides
    @MessageRef
    fun provideMessageRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("chat")

    @Provides
    @SetsRef
    fun provideSetsRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("sets")

    @Provides
    @EffectsRef
    fun provideEffectsRef(
        firestore: FirebaseFirestore
    ): CollectionReference = firestore.collection("effects")

}