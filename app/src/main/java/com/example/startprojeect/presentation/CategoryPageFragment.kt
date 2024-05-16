package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startprojeect.R
import com.example.startprojeect.data.categories
import com.example.startprojeect.data.products
import com.example.startprojeect.databinding.FragmentCategoryPageBinding
import com.example.startprojeect.domain.CategoryViewModel
import com.example.startprojeect.domain.ProductViewModel
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.presentation.adapters.CategoryPageAdapter
import com.example.startprojeect.presentation.adapters.MyHomeRecyclerViewAdapter
import com.example.startprojeect.presentation.adapters.PopularAdapter
import com.example.startprojeect.presentation.adapters.ProductCategoryAdapter
import kotlinx.coroutines.launch


class CategoryPageFragment : Fragment() {
   private lateinit var binding: FragmentCategoryPageBinding
   private lateinit var stateViewModel: StateViewModel
   private lateinit var categoryViewModel: CategoryViewModel
   private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryPageBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]

        stateViewModel.menuVisibility(false)
        binding.arrowbac.setOnClickListener {
            findNavController().navigate(R.id.action_categoryPageFragment_to_homeFragment)
        }
        binding.categoryName.text = categoryViewModel.currentCategory

        var result: List<products>? = null
        lifecycleScope.launch {
            try {
                result = productViewModel.getProductByCategory(categoryViewModel.currentCategoryID)
            }catch (e:Exception){

            }
        }.invokeOnCompletion {
            if (result != null){
                binding.listProsuct.adapter = ProductCategoryAdapter(result!!,requireActivity())
                binding.listProsuct.layoutManager = GridLayoutManager(requireContext(),2)
            }
        }
        var resultCategory: List<categories>? = null
        lifecycleScope.launch {
            try {
                resultCategory = categoryViewModel.getCategoryes()
            }catch (e:Exception){

            }
        }.invokeOnCompletion {
            if (resultCategory != null){
                binding.listCategory.adapter = CategoryPageAdapter(resultCategory!!,requireActivity())
                binding.listCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }
        }

        return binding.root
    }

}