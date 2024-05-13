/**
 * This class created for Home screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.os.Bundle
import android.util.Log
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
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import com.example.startprojeect.data.product
import com.example.startprojeect.data.stock
import com.example.startprojeect.databinding.FragmentHomeBinding
import com.example.startprojeect.databinding.FragmentHomeListBinding
import com.example.startprojeect.domain.CategoryViewModel
import com.example.startprojeect.domain.ProductViewModel
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.domain.StockViewModel
import com.example.startprojeect.presentation.adapters.StockAdapter
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var stockViewModel: StockViewModel
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
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        stockViewModel = ViewModelProvider(requireActivity())[StockViewModel::class.java]

        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = false
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.isVisible = false

        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.hamburgerVisibility(true)
        stateViewModel.shoppingVisibility(true)
        stateViewModel.menuVisibility(true)
        stateViewModel.cardVisibility(true)
        stateViewModel.heartVisibility(false)
        stateViewModel.arrowVisibility(false)

        val shopBag = requireActivity().findViewById<AppCompatImageView>(R.id.shoppingbagred)
        shopBag.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shoppingBagFragment)
        }

        var categorysss: List<category>? = null
        lifecycleScope.launch {
            try {
                categorysss = categoryViewModel.getCategoryes()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (categorysss != null){
                binding.listCategory.adapter = MyHomeRecyclerViewAdapter(categorysss!!)
                binding.listCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }
        }

        var popular: List<product>? = null
        lifecycleScope.launch {
            try {
               popular = productViewModel.getPopular()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (popular != null){
                binding.listPopular.adapter = SneekersAdapter(popular!!)
                binding.listPopular.layoutManager = GridLayoutManager(requireContext(),GridLayoutManager.VERTICAL)
            }
        }
        var stock: List<stock>? = null
        lifecycleScope.launch {
            try {
                stock = stockViewModel.getAllStock()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if(stock != null){
                binding.listStock.adapter = StockAdapter(stock!!)
                binding.listStock.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }
        }


        return binding.root
    }


}