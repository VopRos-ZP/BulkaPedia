package bulkapedia.users

import com.google.firebase.database.ValueEventListener

interface UserRepository {
    /* Database */
    fun listenAll(): ValueEventListener
    suspend fun fetchAll(): List<User>
    suspend fun fetchById(id: String): User?
    suspend fun create(user: User)
    suspend fun update(user: User)
    suspend fun delete(user: User)
    fun removeListener(listener: ValueEventListener)
    /* Auth */
    suspend fun login(email: String, password: String)
    suspend fun signIn(user: User)
    fun logout()
}