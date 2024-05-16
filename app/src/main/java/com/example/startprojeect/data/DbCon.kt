package com.example.startprojeect.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object DbCon {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://jqatajduegdowclsnadh.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpxYXRhamR1ZWdkb3djbHNuYWRoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTU3MDQ5NjYsImV4cCI6MjAzMTI4MDk2Nn0.jk_3kQ-P62z92vnnf5wuV50jSzgkuxRLWNCrzcpOcD0"
    ){
        install(Auth)
        install(Storage)
        install(Postgrest)
    }
}