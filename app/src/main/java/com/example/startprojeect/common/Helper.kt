package com.example.startprojeect.common

import android.app.AlertDialog
import android.content.Context

class Helper{
    companion object{
        fun Alert(context: Context,title: String, message:String,btnOk:String,btnCancel:String){
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnOk) {_,_ ->}
                .setNegativeButton(btnCancel) {_,_ ->}
                .show()
        }
    }
}
