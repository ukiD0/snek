package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.cart
import com.example.startprojeect.data.products
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.runBlocking

class CartViewModel:ViewModel() {
    var _user: UserInfo? = null
    init {
        _user = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun addProductToCart(productInCart: cart){
        DbCon.supabase.from("cart").insert(productInCart){
            select()
        }.decodeSingleOrNull<cart>()
    }
    suspend fun getProduct(): List<products> {
        var listCart = DbCon.supabase.from("cart").select {
            filter {
                cart::user_id eq _user?.id
            }
        }.decodeList<cart>()
        var listProductIdCart = listCart.map { product ->
            product.product_id
        }
        var allProducts = DbCon.supabase.from("products").select().decodeList<products>()
        var sortedProducts = allProducts.filter { product ->
            product.id in listProductIdCart
        }
        return sortedProducts
    }
    suspend fun deleteByID(productId: String){
        DbCon.supabase.from("cart").delete {
            filter {
                cart::product_id eq productId
            }
        }

    }
}