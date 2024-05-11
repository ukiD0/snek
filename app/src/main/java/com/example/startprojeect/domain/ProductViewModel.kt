package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.product
import io.github.jan.supabase.postgrest.from

class ProductViewModel: ViewModel() {

    suspend fun getPopular(): List<product> {
       return DbCon.supabase.from("product").select().decodeList<product>()
    }
}