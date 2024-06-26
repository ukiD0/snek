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
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.databinding.FragmentSignInBinding
import com.example.startprojeect.domain.AuthViewModel
import com.example.startprojeect.domain.StateViewModel
import io.github.jan.supabase.gotrue.user.UserInfo
import io.paperdb.Paper
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var authViewModel: AuthViewModel

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
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        binding.logindtnn.setOnClickListener {
//            binding.scrollID.isVisible = true
//            binding.linearLayoutContainer.isVisible = false
            var result: UserInfo? = null
            lifecycleScope.launch {
                try {
                    binding.ScrollId.isVisible = true
                    binding.linearLayoutID.isVisible = false
                    result = authViewModel.logIn(binding.email.text.toString(),binding.password.text.toString())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                var userIsLogin = Paper.book().read<Boolean>("isUserLogin") ?: false
                if (result != null || userIsLogin){
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                }else{
                    binding.ScrollId.isVisible = false
                    binding.linearLayoutID.isVisible = true
                }
            }

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