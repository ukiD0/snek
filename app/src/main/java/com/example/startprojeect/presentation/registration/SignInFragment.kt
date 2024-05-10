/**
 *
 * This class created for SignIn screen
 * Author Korovkina Valentina
 * Create date 06.05.2024
 *
 * */
package com.example.startprojeect.presentation.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentSignInBinding
import com.example.startprojeect.domain.StateViewModel


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            statusBarColor = resources.getColor(R.color.white)
            navigationBarColor = resources.getColor(R.color.white)
        }
    }
    //initialization code on create view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater,container,false)

        binding.logindtnn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }
        binding.createUserTExt.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registerAccountFragment)
        }
        binding.revoveryText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        return binding.root
    }

}