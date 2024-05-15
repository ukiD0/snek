package com.example.startprojeect.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.favorite_product
import com.example.startprojeect.data.product
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class FavoriteViewModel:ViewModel() {
    private var _user: MutableLiveData<UserInfo> = MutableLiveData()

    init {
        _user.value = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun allFavorite(): List<product> {
        val fav_prod =
            DbCon.supabase.from("favorite_product").select {
                filter {
                    favorite_product::user_id eq _user.value?.id
                }
            }.decodeList<favorite_product>()
        val id_fav_prod= fav_prod.map {
            it.product_id
        }
        val prod = DbCon.supabase.from("product").select().decodeList<product>()
        return prod.filter{product ->
            product.id in id_fav_prod
        }
    }
//    suspend fun getFavoriteId(): List<String?> {
//        val fav_prod =
//            DbCon.supabase.from("favorite_product").select {
//                filter {
//                    favorite_product::user_id eq _user.value?.id
//                }
//            }.decodeList<favorite_product>()
//        return  fav_prod.map {
//            it.product_id
//        }
//    }
//    suspend fun getFavorite(id_fav_prod: List<String?>): List<product> {
//        val prod = DbCon.supabase.from("product").select().decodeList<product>()
//        return prod.filter{product ->
//            product.id in id_fav_prod
//        }
//    }
}