/**
 * This class created for Home screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentHomeBinding
import com.example.startprojeect.databinding.FragmentHomeListBinding
import com.example.startprojeect.domain.StateViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private lateinit var stateViewModel: StateViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            statusBarColor = resources.getColor(R.color.test_back)
            navigationBarColor = resources.getColor(R.color.white)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.hamburgerVisibility(true)
        stateViewModel.shoppingVisibility(true)
        stateViewModel.menuVisibility(true)
        stateViewModel.cardVisibility(true)




        return binding.root
    }


}