package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentProdileBinding
import com.example.startprojeect.domain.StateViewModel


class ProdileFragment : Fragment() {
    private lateinit var binding: FragmentProdileBinding
    private lateinit var stateViewModel: StateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdileBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.arrowVisibility(true)
        stateViewModel.setText("Профиль")
        stateViewModel.menuVisibility(false)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.heartVisibility(false)



        return binding.root
    }

}