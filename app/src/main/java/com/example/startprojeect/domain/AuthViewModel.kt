package com.example.startprojeect.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.gotrue.user.UserInfo

class AuthViewModel:ViewModel() {

    private var _user: MutableLiveData<UserInfo> = MutableLiveData()
    private var _email: String = ""

    val user : LiveData<UserInfo> = _user

    init {
       val userInfo = DbCon.supabase.auth.currentUserOrNull()
    }

    suspend fun createUser(out_email: String, out_password: String): UserInfo? {
         DbCon.supabase.auth.signUpWith(Email){
            email = out_email
            password = out_password
        }
        return DbCon.supabase.auth.currentUserOrNull()
    }

    suspend fun logIn(out_email: String,out_password: String): UserInfo? {
        DbCon.supabase.auth.signInWith(Email){
            email = out_email
            password = out_password
        }
        return DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun logOut(){
        DbCon.supabase.auth.signOut()
    }
    suspend fun modifyUSer(out_password: String): UserInfo? {
        DbCon.supabase.auth.modifyUser {
            password = out_password
        }
        return DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun verificationOTP(out_token:String): UserInfo? {
        DbCon.supabase.auth.verifyEmailOtp(OtpType.Email.EMAIL,_email,out_token)
        return DbCon.supabase.auth.currentUserOrNull()
    }
    suspend fun sendOTP(out_email: String = ""): UserInfo? {
        if (out_email != "") {
            _email = out_email
        }
        DbCon.supabase.auth.resetPasswordForEmail(_email)
        return DbCon.supabase.auth.currentUserOrNull()
    }

}