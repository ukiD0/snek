package com.example.startprojeect.data.repository

import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import io.github.jan.supabase.postgrest.from

class CategoryModel: CategoryInterface {
    override suspend fun getCategoryes(): List<category> {
        return DbCon.supabase.from("category").select().decodeList<category>()
    }

}