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
import androidx.viewpager2.widget.ViewPager2
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
   private lateinit var binding: FragmentSecondBinding

    //Inizialization code on create view\
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)

        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)

        binding.btn.setOnClickListener {
            viewPager.currentItem = 2
            saveState(2)
        }


        return binding.root
    }
    private fun saveState(position: Int){
        val sharedPref = requireActivity().getSharedPreferences("OnBoarding",Context.MODE_PRIVATE)
        sharedPref.edit().apply{
            putInt("boardState",position)
            apply()
        }
    }

}