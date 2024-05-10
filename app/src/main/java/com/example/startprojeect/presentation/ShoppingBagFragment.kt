/**
 * This class created for Shopping Bag screen
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
import com.example.startprojeect.databinding.FragmentShoppingBagListBinding
import com.example.startprojeect.domain.StateViewModel


class ShoppingBagFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBagListBinding
    private lateinit var stateViewModel: StateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBagListBinding.inflate(layoutInflater, container, false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]

        stateViewModel.cardVisibility(true)
        stateViewModel.heartVisibility(false)
        stateViewModel.menuVisibility(false)
        stateViewModel.arrowVisibility(true)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.setText("Корзина")

        return binding.root
    }

}