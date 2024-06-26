/**
 * This class created for Pdf Viewer screen
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.registration

import android.os.Bundle
import android.os.FileUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.databinding.FragmentPdfViewBinding
import com.example.startprojeect.domain.StateViewModel


class PdfViewFragment : Fragment() {
    private lateinit var binding: FragmentPdfViewBinding
    private lateinit var stateViewModel: StateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPdfViewBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        stateViewModel.arrowVisibility(true)
        stateViewModel.cardVisibility(false)
        stateViewModel.upperMenuVIsibility(true)
        val arr = requireActivity().findViewById<AppCompatImageView>(R.id.arrowback)

        arr.setOnClickListener {
            findNavController().navigate(R.id.action_pdfViewFragment_to_registerAccountFragment)
        }

        //from Assets
//        binding.pdfdf.fromAsset("images.pdf").load()
        //From url
        binding.webView.webViewClient = WebViewClient()
//        binding.webView.settings.setSupportZoom(true)
//        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://drive.google.com/file/d/1naRe-1CCOMikzbiarzNd9NFS06u7f5Zv/view?usp=sharing")

        return binding.root
    }

}