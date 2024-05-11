/**
 * This class created for Shopping Bag screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.BottomSheetBarcodeBinding
import com.example.startprojeect.databinding.FragmentShoppingBagListBinding
import com.example.startprojeect.domain.StateViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class ShoppingBagFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBagListBinding
    private lateinit var stateViewModel: StateViewModel

    @SuppressLint("ClickableViewAccessibility")
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
        generateBarCode()
        val gesture = GestureDetector(requireActivity(), object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                try {
                    if (e2.x - e1!!.x > 0){
                        //right to left
                        binding.blueCard.isVisible = true
                        binding.redCard.isVisible = false
                    }else{
                        //left to right
                        binding.blueCard.isVisible = false
                        binding.redCard.isVisible = true
                    }
                    return true
                }catch (e:Exception){
                    Log.e("test", e.message.toString())
                    return false
                }
            }
        })
        binding.cardSneekers.setOnTouchListener { view, motionEvent ->
            gesture.onTouchEvent(motionEvent)
            true
        }

        return binding.root
    }

    fun generateBarCode(code:String = "123412134156423"){
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
        }
    }

}