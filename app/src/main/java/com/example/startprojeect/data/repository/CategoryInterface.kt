package com.example.startprojeect.data.repository

import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import io.github.jan.supabase.postgrest.from

interface CategoryInterface {
    suspend fun getCategoryes(): List<category>


}