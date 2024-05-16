package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.categories
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class CategoryModel: CategoryInterface {
    override suspend fun getCategoryes(): List<categories>? {
        return DbCon.supabase.from("categories").select{
            order("title",Order.ASCENDING)
        } .decodeList<categories>()
    }

}