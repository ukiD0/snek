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
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentOTPVerificationBinding
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher


class OTPVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOTPVerificationBinding

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

        var min = 30
        val timer = object  : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                min--
                if (min <= 0 ){
                    cancel()
                    binding.senadAgain.isEnabled = false
                    binding.timer.isEnabled = true
                    min = 30
                }else{
                    binding.timer.text ="00:$min"
                }
            }

            override fun onFinish() {
            }

        }
        binding.senadAgain.setOnClickListener {
            timer.start()
            binding.timer.isEnabled = false
        }
        timer.start()

        binding.logindtnn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPVerificationFragment_to_newPassswordFragment)
        }
        binding.arrowback.setOnClickListener {
            findNavController().navigate(R.id.action_OTPVerificationFragment_to_forgotPasswordFragment)
        }

        return binding.root
    }

}