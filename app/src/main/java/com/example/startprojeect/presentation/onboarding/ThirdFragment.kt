/**
 * This class created for third screen onBoarding
 * Author Korovkina Valentina
 * Create date 08.05.2024
 *
 * */
package com.example.startprojeect.presentation.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    //Inizialization code on create view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater,container,false)

        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_signInFragment)
            onBoardingFinished()
        }

        return binding.root
    }

    private fun onBoardingFinished(){
        val sharedPred = requireActivity().getSharedPreferences("OnBoarding",Context.MODE_PRIVATE)
        val editor = sharedPred.edit()
        editor.putBoolean("Finidhed",true)
        editor.apply()
    }

}