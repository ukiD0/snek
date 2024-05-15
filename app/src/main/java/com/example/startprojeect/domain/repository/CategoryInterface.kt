package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import io.github.jan.supabase.postgrest.from

interface CategoryInterface {
    suspend fun getCategoryes(): List<category>?

}