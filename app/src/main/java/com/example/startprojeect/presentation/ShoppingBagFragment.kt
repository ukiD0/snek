/**
 * This class created for Shopping Bag screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.products
import com.example.startprojeect.databinding.BottomSheetBarcodeBinding
import com.example.startprojeect.databinding.FragmentShoppingBagListBinding
import com.example.startprojeect.domain.CartViewModel
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.presentation.adapters.MyshopBagRecyclerViewAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.launch


class ShoppingBagFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBagListBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var cartViewModel: CartViewModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBagListBinding.inflate(layoutInflater, container, false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]



        stateViewModel.cardVisibility(false)
        stateViewModel.heartVisibility(false)
        stateViewModel.menuVisibility(false)
        stateViewModel.arrowVisibility(true)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.shoppingVisibility(false)


        binding.ham.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingBagFragment_to_homeFragment)
        }

        generateBarCode()

        var result: List<products>? = null
        lifecycleScope.launch {
            try {
                result = cartViewModel.getProduct()
            }catch (e:Exception){

            }
        }.invokeOnCompletion {
            if (result != null){
                binding.list.adapter = MyshopBagRecyclerViewAdapter(result!!,requireActivity(),lifecycleScope)
                binding.list.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
        binding.oformitZakaz.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingBagFragment_to_shoppBagSecondFragment)
        }

        return binding.root
    }

    fun generateBarCode(code:String = "hello"){
        try {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            //val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_barcode, null)
            val bottomSheetViewBinding = BottomSheetBarcodeBinding.inflate(layoutInflater)

            bottomSheetDialog.setContentView(bottomSheetViewBinding.root)
            bottomSheetDialog.show()
            val bitmap = BarcodeEncoder().encodeBitmap(
                "hello world!",
                BarcodeFormat.CODE_128,
                600,
                200)
            bottomSheetViewBinding.barCodeText.text = code
            bottomSheetViewBinding.barCodeContainer.setImageBitmap(bitmap)

        } catch (e: Exception) {
            Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
        }
    }

}