package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.profile
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage

class BucketViewModel:ViewModel() {
    private var user: UserInfo? = null
    init {
        user = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun getImage(): ByteArray {
        val bucket = DbCon.supabase.storage.from("for_images")
        val image = bucket.downloadAuthenticated("${user?.id}/image1.jpeg")
        return image
    }
    suspend fun uploadImage(byteArray: ByteArray): ByteArray {
        val bucket = DbCon.supabase.storage.from("for_images")
        bucket.upload("${user?.id}/image1.jpeg",byteArray,true)
        val url = DbCon.supabase.storage.from("for_images").publicUrl("${user?.id}/image1.jpeg")
        DbCon.supabase.from("profile").update({
            profile::avatar setTo url
        }
        ){
            select()
            filter {
                profile::user_id eq user?.id
            }
        }
        return byteArray
    }


}