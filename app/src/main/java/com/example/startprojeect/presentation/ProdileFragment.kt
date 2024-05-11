package com.example.startprojeect.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.databinding.FragmentProdileBinding
import com.example.startprojeect.domain.StateViewModel


class ProdileFragment : Fragment() {
    private lateinit var binding: FragmentProdileBinding
    private lateinit var stateViewModel: StateViewModel
//
//    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent(){
//        binding.photo.setImageURI(it)
//        sendImageToServer(it!!)
//    })
//    val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        if (it.resultCode == Activity.RESULT_OK){
//            val res = it.data!!.extras?.get("data") as Bitmap
//            binding.photo.setImageBitmap(res)
//            sendImageToServer(res)
//        }
//    }

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

        binding.editPhoto.setOnClickListener {

        }

        return binding.root
    }
//    fun showImagePickerDialog(context: Context){
//        val option = arrayOf("Gallery","Camera")
//        AlertDialog.Builder(context)
//            .setTitle("Выберите источник изображения")
//            .setItems(option) {_,which ->
//                when (which){
//                    0 -> openGallery()
//                    1 -> openCamera()
//                }
//            }
//            .show()
//    }
//    private fun openGallery(){
//        galleryLauncher.launcher("images/*")
//    }
//    private fun openCamera(){
//        if (ActivityCompat.checkSelfPermission(
//                requireActivity(),
//                android.Manifest.permission.CAMERA
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(requireActivity(), arrayOf(
//                android.Manifest.permission.CAMERA),123)
//        }else{
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////            val values:ContentValues = ContentValues()
////            intent.putExtra(MediaStore.EXTRA_OUTPUT, values)
//            cameraLauncher.launch(intent)
//        }
//    }

}