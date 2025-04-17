package ru.bulkapedia.data.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage

class SupabaseHelper {

    private val client = createSupabaseClient(
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR2bGR1dXVidW5xZGF3dXVqcGdpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDQxNDYxNDksImV4cCI6MjA1OTcyMjE0OX0.gDFxG5jpKxgzx-AGYhQP15OvfYy43p0hICMVKUkysqo",
        supabaseUrl = "https://dvlduuubunqdawuujpgi.supabase.co",
    ) {
        install(Auth)
        install(Storage)
        install(Realtime)
        install(Postgrest)
    }

    val auth = client.auth
    val storage = client.storage
    val realtime = client.realtime
    val postgrest = client.postgrest

}