/**
 * This class created for RegisterAccount screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
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
import com.example.startprojeect.databinding.FragmentRegisterAccountBinding
import com.example.startprojeect.domain.AuthViewModel
import com.example.startprojeect.domain.StateViewModel
import com.github.barteksc.pdfviewer.PDFView
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.launch


class RegisterAccountFragment : Fragment() {
    private lateinit var binding: FragmentRegisterAccountBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            statusBarColor = resources.getColor(R.color.white)
            navigationBarColor = resources.getColor(R.color.white)
        }
    }
    //inizialization code on create view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterAccountBinding.inflate(inflater,container,false)
        stateViewModel= ViewModelProvider(requireActivity())[StateViewModel::class.java]
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        stateViewModel.upperMenuVIsibility(false)

        binding.arrowback.setOnClickListener {
            findNavController().navigate(R.id.action_registerAccountFragment_to_signInFragment)
        }

        binding.logindtnn.setOnClickListener {
            var result: UserInfo? = null
            lifecycleScope.launch {
                try {
                    result =  authViewModel.createUser(binding.email.text.toString(),binding.password.text.toString())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                if (result != null){
                    findNavController().navigate(R.id.action_registerAccountFragment_to_homeFragment)
                }
            }
        }

        binding.createUserTExt.setOnClickListener {
            findNavController().navigate(R.id.action_registerAccountFragment_to_signInFragment)
        }
        binding.pdf.setOnClickListener {
            findNavController().navigate(R.id.action_registerAccountFragment_to_pdfViewFragment)
        }

        return  binding.root
    }

}