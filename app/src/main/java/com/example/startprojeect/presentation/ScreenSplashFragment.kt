/**
 * This class created for Splash screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentScreenSplashBinding


class ScreenSplashFragment : Fragment() {
    private lateinit var binding: FragmentScreenSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenSplashBinding.inflate(inflater,container,false)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_screenSplashFragment_to_viewPagerFragment)
        },3000)

        return binding.root
    }


}