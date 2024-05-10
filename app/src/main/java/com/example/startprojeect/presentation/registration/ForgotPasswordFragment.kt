/**
 * This class created for Forgot Password screen
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.registration

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
   private lateinit var binding: FragmentForgotPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            navigationBarColor = resources.getColor(R.color.white)
            statusBarColor = resources.getColor(R.color.white)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)

        binding.arrowback.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_registerAccountFragment)
        }

        binding.logindtnn.setOnClickListener {
            val ViewAlert = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog_for_email,null)
            val alert = AlertDialog.Builder(requireContext())
                alert.setView(ViewAlert)
                val dialog = alert.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_OTPVerificationFragment)
        }





        return binding.root
    }


}