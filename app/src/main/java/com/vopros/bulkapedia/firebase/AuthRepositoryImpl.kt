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

    override suspend fun register(
        user: User,
        onError: (String) -> Unit,
        onSuccess: (User) -> Unit
    ) {

        TODO("Not yet implemented")
    }

    override suspend fun login(
        email: String,
        password: String,
        onError: (String) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { it.message?.let(onError) }
            .addOnSuccessListener {
                val uid = it.user!!.uid

            }
            .await()
    }

    override fun logout() {
        auth.signOut()
    }
}