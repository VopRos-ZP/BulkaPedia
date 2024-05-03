package ru.bulkapedia.data.repository

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.PostgrestRequestBuilder
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.postgresSingleDataFlow
import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.UserRepository
import ru.bulkapedia.domain.utils.Table
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    realtime: Realtime
) : UserRepository {

    private val channel = realtime.channel("users")
    private val table = Table.USERS
    private val eqFilter: PostgrestRequestBuilder.(String) -> Unit = { id -> filter { User::id eq id } }

    override suspend fun fetchAll(): List<User> {
        return postgrest.from(table)
            .select()
            .decodeList()
    }

    override suspend fun fetchById(id: String): User {
        return postgrest.from(table)
            .select { eqFilter(this, id) }
            .decodeSingle()
    }

    override suspend fun upsert(user: User) {
        postgrest.from(table).upsert(user)
    }

    override suspend fun delete(id: String) {
        postgrest.from(table).delete { eqFilter(this, id) }
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun listenAll(): Flow<List<User>> {
        return channel.postgresListDataFlow(table = table, primaryKey = User::id)
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun listenById(id: String): Flow<User> {
        return channel.postgresSingleDataFlow(table = table, primaryKey = User::id) {
            User::id eq id
        }
    }

    override suspend fun subscribe() {
        channel.subscribe()
    }

    override suspend fun dispose() {
        channel.unsubscribe()
    }
}