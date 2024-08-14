package ru.bulkapedia.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.Json
import ru.bulkapedia.config.ConfigApp

class SupabaseWrapper {

    private val client = createSupabaseClient(
        supabaseUrl = ConfigApp.URL,
        supabaseKey = ConfigApp.API_KEY
    ) {
        defaultSerializer = KotlinXSerializer(Json {
            ignoreUnknownKeys = true
        })
        install(Auth)
        install(Storage)
        install(Realtime)
        install(Postgrest) {
            this.defaultSchema = ConfigApp.PROD_SCHEMA
        }
    }

    val auth = client.auth
    val storage = client.storage
    val realtime = client.realtime
    val postgres = client.postgrest

}