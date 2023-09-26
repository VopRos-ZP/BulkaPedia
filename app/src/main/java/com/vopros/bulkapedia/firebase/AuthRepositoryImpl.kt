package com.vopros.bulkapedia.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.user.User
import com.vopros.bulkapedia.user.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository
) : AuthRepository {

    private val auth = Firebase.auth

    override suspend fun sendPasswordResetCode(email: String, onError: (String) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnFailureListener { it.message?.let(onError) }
            .await()
    }

    override suspend fun register(
        user: User,
        onError: (String) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        val result = auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnFailureListener { it.message?.let(onError) }
            .await()
        if (result.user != null) {
            val token = result.user!!.uid
            userRepository.update(user.copy(id = token))
            onSuccess(userRepository.fetchOne(token))
        }
    }

    override suspend fun login(
        email: String,
        password: String,
        onError: (String) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        val result = auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { it.message?.let(onError) }
            .await()
        if (result.user != null) {
            onSuccess(userRepository.fetchOne(result.user!!.uid))
        }
    }

    override fun logout() {
        auth.signOut()
    }
}