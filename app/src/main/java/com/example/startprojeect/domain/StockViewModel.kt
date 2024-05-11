package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.stock
import io.github.jan.supabase.postgrest.from

class StockViewModel: ViewModel() {


    suspend fun getAllStock(): List<stock> {
        return DbCon.supabase.from("stock").select().decodeList<stock>()
    }
}