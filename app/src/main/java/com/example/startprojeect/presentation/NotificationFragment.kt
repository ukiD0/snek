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
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentNotificationListBinding
import com.example.startprojeect.domain.StateViewModel


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationListBinding
    private lateinit var stateViewModel: StateViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentNotificationListBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]

        stateViewModel.setText("Уведомления")
        stateViewModel.arrowVisibility(true)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        stateViewModel.menuVisibility(false)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.heartVisibility(false)
        stateViewModel.cardVisibility(false)

        return binding.root
    }


}