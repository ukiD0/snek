/**
 * This class create for OnBoarding Screens
 * Author Korovkina Valentina
 * Create date 06.06.2024
 *
 * */
package com.example.startprojeect.presentation.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.startprojeect.presentation.adapters.ViewPagerAdapter
import com.example.startprojeect.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    //inizialization on create view code
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        val adapter = ViewPagerAdapter(
            requireActivity(),
            lifecycle
        )

        val shredPref = requireActivity().getSharedPreferences("OnBoarding",Context.MODE_PRIVATE)
        val state = shredPref.getInt("boardState",0)

//        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = state

        return binding.root
    }

}