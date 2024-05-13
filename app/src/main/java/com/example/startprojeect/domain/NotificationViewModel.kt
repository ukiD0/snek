package com.example.startprojeect.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.notifications
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.from

class NotificationViewModel:ViewModel() {

    private  var _user: MutableLiveData<UserInfo> = MutableLiveData()
    init{
        _user.value = DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun getNotifications(): List<notifications> {
        return DbCon.supabase.from("notifications").select{
                filter {
                    notifications::user_id eq _user.value?.id
                }
        }.decodeList<notifications>()
    }
}