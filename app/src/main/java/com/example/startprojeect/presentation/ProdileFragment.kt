package com.example.startprojeect.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.Manifest
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.profile
import com.example.startprojeect.databinding.BottomSheetBarcodeBinding
import com.example.startprojeect.databinding.FragmentProdileBinding
import com.example.startprojeect.domain.BucketViewModel
import com.example.startprojeect.domain.KandinskiiViewModel
import com.example.startprojeect.domain.ProdileViewModel
import com.example.startprojeect.domain.StateViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream


class ProdileFragment : Fragment() {
    private lateinit var binding: FragmentProdileBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var prodileViewModel: ProdileViewModel
    private lateinit var bucketViewModel: BucketViewModel
    private lateinit var kandinskiiViewModel: KandinskiiViewModel

    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.photo.setImageURI(it)
        sendImageToServer(it!!)
    }
    val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val res = it.data!!.extras?.get("data") as Bitmap
            binding.photo.setImageBitmap(res)
            sendImageToServer(res)
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        PermissionX.init(this)
//            .permissions(Manifest.permission.CAMERA)
//            .request { allGranted, deniedList ->
//                if (allGranted) {
//                    // Разрешение получено, можно выполнять операции, требующие доступа к камере
//                    cameraLauncher.launch(intent)
//                } else {
//                    // Один или несколько разрешений были отклонены, нужно обработать эту ситуацию
//                }
//            }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdileBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        prodileViewModel = ViewModelProvider(requireActivity())[ProdileViewModel::class.java]
        bucketViewModel = ViewModelProvider(requireActivity())[BucketViewModel::class.java]
        kandinskiiViewModel = ViewModelProvider(requireActivity())[KandinskiiViewModel::class.java]

        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = true
        ready.setText("Готово")
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.setText("Профиль")
        mainText.isVisible = true

        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.arrowVisibility(true)
        stateViewModel.cardVisibility(false)
        stateViewModel.menuVisibility(false)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.heartVisibility(false)
        stateViewModel.cardVisibility(false)
        stateViewModel.shoppingVisibility(false)

        var arrowBack = requireActivity().findViewById<AppCompatImageView>(R.id.arrowback)
        arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editPhoto.setOnClickListener {
            showImagePickerDialog(requireContext())
        }
        var result: profile? = null
        lifecycleScope.launch {
            try {
                result = prodileViewModel.getProfileData()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (result != null){
                binding.nameEditText.setText(result?.name.toString())
                binding.surnameEditText.setText(result?.surname.toString())
                binding.adressEditText.setText(result?.location.toString())
                binding.telephoneEditText.setText(result?.phone.toString())
            }
        }
        ready.setOnClickListener {
            var res: profile? = null
            lifecycleScope.launch{
                try {
                    res = prodileViewModel.setProfileData(
                        profile(
                            name = binding.nameEditText.text.toString(),
                            surname = binding.surnameEditText.text.toString(),
                            location = binding.adressEditText.text.toString(),
                            phone = binding.telephoneEditText.text.toString()
                        )
                    )
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                if (res != null){
                    Toast.makeText(requireContext(),"Инофрмация обновлена",Toast.LENGTH_LONG).show()
                }
            }
        }

        var image: ByteArray? = null
        lifecycleScope.launch {
            try {
                image = bucketViewModel.getImage()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (image != null){
                binding.photo.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image!!.size))
            }
        }

        binding.barcode.setOnClickListener {
            barcodeForDialog()
            setFullBright()
        }

        generateBArCode()
        return binding.root
    }
    fun generateBArCode(code: String = "1234"){
        try {
            val bitmap = BarcodeEncoder().encodeBitmap(
                "from data base string",
                BarcodeFormat.CODE_128,
                600,
                200
            )
            binding.barcode.setImageBitmap(bitmap)
        }catch (e:Exception){
            Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
        }
    }
    fun barcodeForDialog(code: String = "112"){
        try {
            val BottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetViewBinding = BottomSheetBarcodeBinding.inflate(layoutInflater)
            BottomSheetDialog.setContentView(bottomSheetViewBinding.root)
            BottomSheetDialog.show()
            val bitmap = BarcodeEncoder().encodeBitmap(
                "from data string",
                BarcodeFormat.CODE_128,
                600,
                200
            )
            bottomSheetViewBinding.barCodeContainer.setImageBitmap(bitmap)
        }catch (e:Exception){
            Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
        }
    }

    private fun setFullBright() {
        val windowParams = activity?.window?.attributes
        windowParams?.screenBrightness = 0.8f
        activity?.window?.attributes = windowParams
    }
    private fun showImagePickerDialog(context: Context){
        val option = arrayOf("Gallery","Camera","Generate by Fusion Brain")
        AlertDialog.Builder(context)
            .setTitle("Выберите источник изображения")
            .setItems(option) {_,which ->
                when (which){
                    0 -> openGallery()
                    1 -> openCamera()
                    2 -> generateImage()
                }
            }
            .show()
    }
    private fun openGallery(){
        galleryLauncher.launch("images/*")
    }
    private fun openCamera(){
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(
                android.Manifest.permission.CAMERA),123)
        }else{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            val values:ContentValues = ContentValues()
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, values)
            cameraLauncher.launch(intent)
        }
    }
    private fun sendImageToServer(uri: Uri){
        var inputStream: InputStream? = null
        try {
            inputStream = requireActivity().contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val outPutStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outPutStream)
            lifecycleScope.launch {
                try {
                    bucketViewModel.uploadImage(outPutStream.toByteArray())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }
        }catch (e:Exception){
            Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
        }finally {
            inputStream?.close()
        }
    }
    private fun sendImageToServer(bitmap: Bitmap){
        try {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
            lifecycleScope.launch {
                try {
                    bucketViewModel.uploadImage(outputStream.toByteArray())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }
        }catch (e:Exception){
            Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
        }
    }
    private fun generateImage(){
        Log.e("CliskME","Clidck,me")
        kandinskiiViewModel.generateImage("Котенок")
        Log.e("TryGEnerate","generatter")

        lifecycleScope.launch {
            delay(5000)
            kandinskiiViewModel.getImage()
        }.invokeOnCompletion {
            if (kandinskiiViewModel.bitmap  != null){
                binding.photo.setImageBitmap(kandinskiiViewModel.bitmap)
            }
        }
    }


}