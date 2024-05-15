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
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.favorite_product
import com.example.startprojeect.data.product
import com.example.startprojeect.databinding.FragmentFavoriteListBinding
import com.example.startprojeect.domain.FavoriteViewModel
import com.example.startprojeect.domain.ProdileViewModel
import com.example.startprojeect.domain.ProductViewModel
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.presentation.adapters.MyFavoriteRecyclerViewAdapter
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteListBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var productViewModel: ProductViewModel
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
        favoriteViewModel = ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]

        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = false
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.setText("Избранное")
        mainText.isVisible = true
        stateViewModel.cardVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.menuVisibility(true)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.arrowVisibility(true)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.heartVisibility(true)
        stateViewModel.setText("Избранное")

        val arrback = requireActivity().findViewById<AppCompatImageView>(R.id.arrowback)
        arrback.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }

        var result: List<product>? = null
        lifecycleScope.launch {
            try {
                result = favoriteViewModel.allFavorite()
            }catch (e:Exception){
                Helper.Alert(requireActivity(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (result != null){
                binding.list.adapter = MyFavoriteRecyclerViewAdapter(result!!)
                binding.list.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }


        return binding.root
    }

}