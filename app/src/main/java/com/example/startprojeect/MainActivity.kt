package com.example.startprojeect

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        test hide navigation bar
//        window.decorView.apply {
//            // Hide both the navigation bar and the status bar.
//            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
//            // a general rule, you should design your app to hide the status bar whenever you
//            // hide the navigation bar.
//            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
        setFullBright()

    }
    private fun setFullBright() {
            val windowParams = window.attributes
            windowParams.screenBrightness = 0.8f
            window.attributes = windowParams
        Log.e("test",windowParams.screenBrightness.toString())
        Log.e("test2",window.attributes.toString())
    }

}