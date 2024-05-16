package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.actions
import io.github.jan.supabase.postgrest.from

class StockViewModel: ViewModel() {


    suspend fun getAllStock(): List<actions> {
        return DbCon.supabase.from("actions").select().decodeList<actions>()
    }
}