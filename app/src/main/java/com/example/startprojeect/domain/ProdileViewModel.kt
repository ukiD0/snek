package com.example.startprojeect.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.profile
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from

class ProdileViewModel:ViewModel() {
    private  var _user: MutableLiveData<UserInfo?> = MutableLiveData()

    init {
        _user.value = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun setProfileData(profileData : profile): profile? {
        profileData.user_id = _user.value?.id
        val check = DbCon.supabase.from("profile").select {
            filter {
                profile::user_id eq  _user.value?.id
            }
        }.decodeSingleOrNull<profile>()
        if (check != null){
           return DbCon.supabase.from("profile").update(
                {
                    profile::phone setTo  profileData.phone
                    profile::firstname setTo profileData.firstname
                    profile::lastname setTo profileData.lastname
                    profile::address  setTo profileData.address
                }
            ){
                select()
                filter {
                    profile::user_id eq profileData.user_id
                }
            }.decodeSingleOrNull<profile>()
        }else{
            val insert = DbCon.supabase.from("profile").insert(profileData){
                select()
            }.decodeSingleOrNull<profile>()
            return insert
        }
    }
    suspend fun getProfileData(): profile? {
        val getData = DbCon.supabase.from("profile").select {
            filter {
                profile::user_id eq _user.value?.id
            }
        }.decodeSingleOrNull<profile>()
        return getData
    }


}