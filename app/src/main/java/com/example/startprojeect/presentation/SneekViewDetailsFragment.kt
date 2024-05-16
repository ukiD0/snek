package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentSneekViewDetailsBinding
import com.example.startprojeect.domain.CategoryViewModel
import com.example.startprojeect.domain.ProductViewModel
import com.squareup.picasso.Picasso

class SneekViewDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSneekViewDetailsBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSneekViewDetailsBinding.inflate(inflater,container,false)
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]

        if (productViewModel.currentSelectedProduct != null){
            binding.nameSneekers.text = productViewModel.currentSelectedProduct!!.title.toString()
            binding.priceSneekers.text = "₽" + productViewModel.currentSelectedProduct!!.cost.toString()
            Picasso.get().load(productViewModel.currentSelectedProduct!!.photo).into(binding.photoFromServer)
            binding.description.text = productViewModel.currentSelectedProduct!!.description
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}