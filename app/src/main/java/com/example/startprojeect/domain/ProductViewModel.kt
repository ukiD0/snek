package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.products
import io.github.jan.supabase.postgrest.from

class ProductViewModel: ViewModel() {
    var currentProductUUID = ""
    var currentSelectedProduct: products? = null
    suspend fun getPopular(): List<products> {
       return DbCon.supabase.from("products").select{
           filter {
               products::is_best_seller eq true
           }
       } .decodeList<products>()
    }
    suspend fun getProductByCategory(categoryId: String): List<products> {
        if (categoryId == ""){
            return DbCon.supabase.from("products").select().decodeList<products>()
        }else{
            return DbCon.supabase.from("products").select{
                filter {
                    products::category_id eq categoryId
                }
            } .decodeList<products>()
        }
    }
}