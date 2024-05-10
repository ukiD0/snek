package com.example.startprojeect.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object DbCon {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://sghfkpwrjbuknwyhgmle.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNnaGZrcHdyamJ1a253eWhnbWxlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUxODgxNDIsImV4cCI6MjAzMDc2NDE0Mn0.s-FrXiBpSA1I7CgleApCgxmv61hb62NjaaEVOTfAwjo"
    ){
        install(Auth)
        install(Storage)
        install(Postgrest)
    }
}