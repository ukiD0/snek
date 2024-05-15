package com.example.startprojeect.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.MenuSideLayoutBinding

class SideMenuFragment: Fragment() {
    private lateinit var binding: MenuSideLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuSideLayoutBinding.inflate(inflater,container,false)

        binding.shopBag.setOnClickListener {
            Log.e("testNAv","teststdas")
            findNavController().navigate(R.id.action_sideMenuFragment_to_homeFragment)
        }

        return binding.root
    }
}