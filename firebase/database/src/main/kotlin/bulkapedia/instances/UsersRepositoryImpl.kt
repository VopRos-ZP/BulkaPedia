package bulkapedia.instances

import bulkapedia.Callback
import bulkapedia.users.User
import bulkapedia.users.UserDTO
import bulkapedia.users.UserRepository
import bulkapedia.users.toPOJO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class UsersRepositoryImpl(
    private val ref: DatabaseReference,
    private val auth: FirebaseAuth
) : UserRepository {

    private fun transform(s: DataSnapshot): User? {
        return s.getValue(UserDTO::class.java)?.let { dto ->
            User(s.key ?: "",
                dto.email, dto.password,
                dto.nickname, dto.updateEmail,
                dto.updateNickname
            )
        }
    }

    override fun listenAll(callback: Callback<List<User>>): ValueEventListener {
        return ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull { transform(it) }.apply(callback.onSuccess)
            }

            override fun onCancelled(error: DatabaseError) {
                callback.onError?.invoke(error.message)
            }
        })
    }

    override suspend fun fetchAll(): List<User> {
        return ref.get().await().children.mapNotNull { transform(it) }
    }

    override suspend fun fetchById(id: String): User? {
        return fetchAll().find { it.userId == id }
    }

    override suspend fun create(user: User) {
        ref.push().setValue(user.toDTO()).await()
    }

    override suspend fun update(user: User) {
        ref.child(user.userId).setValue(user.toDTO()).await()
    }

    override suspend fun delete(user: User) {
        ref.child(user.userId).removeValue().await()
    }

    override fun removeListener(listener: ValueEventListener) {
        ref.removeEventListener(listener)
    }

    private fun User.toDTO() = UserDTO(email, password, nickname, updateEmail, updateNickname)

    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        auth.signOut()
    }

    override suspend fun signIn(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .await().user?.uid?.let { uid -> update(user.copy(userId = uid)) }
    }
}