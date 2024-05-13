/**
 * This class created for Splash screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation.onboarding

import android.content.Context
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
import com.example.startprojeect.presentation.registration.SignInFragment


class ScreenSplashFragment : Fragment() {
    private lateinit var binding: FragmentScreenSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenSplashBinding.inflate(inflater,container,false)

        Handler(Looper.getMainLooper()).postDelayed({
            onBoardingFinish()
        },3000)

        return binding.root
    }
    private fun onBoardingFinish(){
        var sharedPref = requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        var isFinished = sharedPref.getBoolean("Finished",false)
        if (isFinished){
            findNavController().navigate(R.id.action_screenSplashFragment_to_signInFragment)
        }else{
            findNavController().navigate(R.id.action_screenSplashFragment_to_viewPagerFragment)
        }
    }

}