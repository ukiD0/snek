package com.example.startprojeect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentOrderListBinding
import com.example.startprojeect.domain.StateViewModel


class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderListBinding
    private lateinit var stateViewModel: StateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderListBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = false
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.setText("Заказы")
        mainText.isVisible = true




        return binding.root
    }


}