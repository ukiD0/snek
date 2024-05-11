/**
 * This class created for Main Activity screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.ApiKey
import com.example.startprojeect.databinding.ActivityMainBinding
import com.example.startprojeect.databinding.FragmentSignInBinding
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.presentation.registration.SignInFragment
import com.yandex.mapkit.MapKitFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var stateViewModel: StateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        MapKitFactory.setApiKey(ApiKey.MAPKIT_API_KEY)

        setContentView(binding.root)

        stateViewModel = ViewModelProvider(this)[StateViewModel::class.java]

        val arrowback = findViewById<AppCompatImageView>(R.id.arrowback)
        val hamburger = findViewById<AppCompatImageView>(R.id.hamburgermenu)
        val maintext = findViewById<AppCompatImageView>(R.id.maintext)
        val shoppingbagred = findViewById<AppCompatImageView>(R.id.shoppingbagred)
        val cardviewmain = findViewById<CardView>(R.id.cardviewmain)
        val botmenu = findViewById<CoordinatorLayout>(R.id.menumainID)
        val heart = findViewById<AppCompatImageView>(R.id.hearticon)
        val text = findViewById<AppCompatTextView>(R.id.texttexttext)
        val ready = findViewById<AppCompatTextView>(R.id.textReady)

        stateViewModel.textReady.observe(this){
            if (it != null){
                ready.text = it.toString()
            }
        }
        stateViewModel.heart.observe(this){
            if (it != null){
                heart.isVisible = it
            }
        }
        stateViewModel.text.observe(this){
            if (it != null){
                text.text = it.toString()
            }
        }
        stateViewModel.arrow.observe(this){
            if (it != null){
                arrowback.isVisible  = it
            }
        }
        stateViewModel.menu.observe(this){
            if (it != null){
                botmenu.isVisible = it
            }
        }
       stateViewModel.alluppermenu.observe(this){
           if (it != null){
               cardviewmain.isVisible = it
           }
       }
        stateViewModel.hambugerr.observe(this){
            if (it != null){
                hamburger.isVisible  = it
            }
        }
        stateViewModel.cardvisible.observe(this){
            if (it != null){
                maintext.isVisible = it
            }
        }
        stateViewModel.shoppingbag.observe(this){
            if (it != null){
                shoppingbagred.isVisible = it
            }
        }

        setFullBright()
        binding.btmNAV.background = null
        binding.bottomAppBar.setBackgroundDrawable(resources.getDrawable(R.drawable.noshd))

        val navController = findNavController(R.id.fragment_container_view_tag)
        binding.btmNAV.setupWithNavController(navController)

        }
    private fun setFullBright() {
        val windowParams = window.attributes
        windowParams.screenBrightness = 0.8f
        window.attributes = windowParams
    }


}