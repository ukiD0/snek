package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentShoppBagSecondBinding


class ShoppBagSecondFragment : Fragment() {
    private lateinit var binding: FragmentShoppBagSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppBagSecondBinding.inflate(inflater,container,false)


        return binding.root
    }

}