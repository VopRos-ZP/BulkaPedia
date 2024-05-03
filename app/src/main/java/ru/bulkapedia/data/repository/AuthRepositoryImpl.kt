package ru.bulkapedia.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.bulkapedia.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth = Firebase.auth

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUp(email: String, password: String): String? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user?.uid
    }

    override suspend fun logout() {
        auth.signOut()
    }

}