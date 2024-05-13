/**
 * This class created for Notifications screen
 * Author Korovkina Valentina
 * Created date 09.05.2024
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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.notifications
import com.example.startprojeect.databinding.FragmentNotificationListBinding
import com.example.startprojeect.domain.NotificationViewModel
import com.example.startprojeect.domain.StateViewModel
import kotlinx.coroutines.launch


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationListBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var notificationViewModel: NotificationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationListBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        notificationViewModel = ViewModelProvider(requireActivity())[NotificationViewModel::class.java]
        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = false
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.setText("Уведомления")
        mainText.isVisible = true

        stateViewModel.arrowVisibility(true)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.menuVisibility(false)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.heartVisibility(false)
        stateViewModel.cardVisibility(false)
        var arrback = requireActivity().findViewById<AppCompatImageView>(R.id.arrowback)

        arrback.setOnClickListener {
            findNavController().popBackStack()
        }

        var result: List<notifications>? = null
        lifecycleScope.launch {
            try {
                result = notificationViewModel.getNotifications()
            }catch (e:Exception){
                Helper.Alert(requireActivity(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (result != null){
                binding.list.adapter = MyNotificationRecyclerViewAdapter(result!!)
                binding.list.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }


        return binding.root
    }


}