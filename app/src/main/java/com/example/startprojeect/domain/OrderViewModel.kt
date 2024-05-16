package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.orders
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from

class OrderViewModel:ViewModel() {
    var _user: UserInfo? = null
    init {
        _user = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun pushOrder(out_orders: orders): orders? {
        return DbCon.supabase.from("orders").insert(out_orders){
            select()
        }.decodeSingleOrNull<orders>()
    }
    suspend fun getOrder(): orders? {
        return DbCon.supabase.from("orders").select {
            filter {
                orders::user_id eq _user?.id
            }
        }.decodeSingleOrNull<orders>()
    }

}