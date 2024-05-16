package com.example.startprojeect.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.orders
import com.example.startprojeect.databinding.FragmentShoppBagSecondBinding
import com.example.startprojeect.domain.OrderViewModel
import com.permissionx.guolindev.PermissionX
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import kotlinx.coroutines.launch
import java.util.Locale


class ShoppBagSecondFragment : Fragment() {
    private lateinit var binding: FragmentShoppBagSecondBinding
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppBagSecondBinding.inflate(inflater,container,false)

        PermissionX.init(this)
            .permissions(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            .request {_,_,_ -> }

        var result: orders? = null
        lifecycleScope.launch {
            try {
                result = orderViewModel.pushOrder(
                    orders(
                        email =  binding.email.text.toString(),
                        phone = binding.tekphone.text.toString(),
                        address = binding.adrresss.text.toString(),
                        payment_id = null,
                        delivery_coast = (300..1000).random()
                    )
                )
            }catch (e:Exception){

            }
        }.invokeOnCompletion {
            if (result != null){
                val ViewAlert = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog_for_email,null)
                val alert = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                alert.setView(ViewAlert)
                val dialog = alert.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setOnDismissListener{
                    findNavController().navigate(R.id.action_shoppBagSecondFragment_to_homeFragment)
                }
                dialog.show()
            }
        }
        binding.mapFromYandex.map.addInputListener(object : InputListener{
            override fun onMapTap(p0: Map, p1: Point) {
                try {
                    Log.e("metka11","???")
                    val geocoder = Geocoder(requireActivity(), Locale("ru-RU"))
                    val metka = geocoder.getFromLocation(p1.latitude,p1.longitude,1)
                    Log.e("metka11",metka.toString())
                    if (metka != null){
                        binding.adrresss.text = metka[0].getAddressLine(0)
                    }
                }catch (e:Exception){
                    Log.e("oshibka","oshibka")
                }

            }
            override fun onMapLongTap(p0: Map, p1: Point) {
                Log.e("metka22","???")
                val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                val metka = geocoder.getFromLocation(p1.latitude,p1.longitude,1)
                Log.e("metka22",metka.toString())
            }
        })
        MapKitFactory.getInstance().onStart()
        binding.mapFromYandex.onStart()

        return binding.root
    }
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
       binding.mapFromYandex.onStart()
    }

    override fun onStop() {
        binding.mapFromYandex.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}