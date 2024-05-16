package com.example.startprojeect.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentOrderListBinding
import com.example.startprojeect.domain.StateViewModel

class PopularFragment: Fragment() {
    private lateinit var binding: FragmentOrderListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderListBinding.inflate(inflater,container,false)

        return binding.root
    }

}