/**
 * This class created for OTP verification screen
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.registration

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.databinding.FragmentOTPVerificationBinding
import com.example.startprojeect.domain.AuthViewModel
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class OTPVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOTPVerificationBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            statusBarColor = resources.getColor(R.color.white)
            navigationBarColor = resources.getColor(R.color.white)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPVerificationBinding.inflate(inflater,container,false)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val timer = object  : CountDownTimer(30000,1000){
            override fun onTick(milSec: Long) {
               binding.timer.text = "00:${milSec/1000}"
                binding.senadAgain.isEnabled = false
            }
            override fun onFinish() {
                binding.timer.text = "00:00"
                binding.senadAgain.isEnabled = true
            }

        }
        binding.senadAgain.setOnClickListener {
            lifecycleScope.launch {
                try {
                    authViewModel.sendOTP()
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                timer.start()
            }
        }
        timer.start()

        binding.logindtnn.setOnClickListener {
            var result: UserInfo? = null
            lifecycleScope.launch {
                try {
                    result = authViewModel.verificationOTP(
                        binding.one.text.toString()
                                + binding.two.text.toString()
                                + binding.three.text.toString()
                                + binding.four.text.toString()
                                + binding.five.text.toString()
                                + binding.six.text.toString())
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                if (result != null){
                    findNavController().navigate(R.id.action_OTPVerificationFragment_to_newPassswordFragment)
                }
            }
        }

        binding.arrowback.setOnClickListener {
            findNavController().navigate(R.id.action_OTPVerificationFragment_to_forgotPasswordFragment)
        }

        return binding.root
    }

}