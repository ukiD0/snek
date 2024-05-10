/**
 * This class created for Favorite screen
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
import com.example.startprojeect.databinding.FragmentFavoriteListBinding
import com.example.startprojeect.domain.StateViewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteListBinding
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
        binding = FragmentFavoriteListBinding.inflate(layoutInflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]

        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.menuVisibility(true)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.arrowVisibility(true)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.heartVisibility(true)
        stateViewModel.setText("Избранное")

        return binding.root
    }

}