/**
 * This class created for New PAssword screen
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.databinding.FragmentNewPassswordBinding
import com.example.startprojeect.domain.AuthViewModel
import kotlinx.coroutines.launch


class NewPassswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPassswordBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPassswordBinding.inflate(layoutInflater,container,false)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        binding.logindtnn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    authViewModel.modifyUSer(binding.pass2.text.toString())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                findNavController().navigate(R.id.action_newPassswordFragment_to_homeFragment)
            }
        }

        binding.arrowback.setOnClickListener {
            findNavController().navigate(R.id.action_newPassswordFragment_to_OTPVerificationFragment)
        }
        return binding.root
    }
}