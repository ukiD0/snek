/**
 * This class created for Home screen
 * Author Korovkina Valentina
 * Created date 06.05.2024
 * */
package com.example.startprojeect.presentation

import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.text.BoringLayout.Metrics
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.startprojeect.R
import com.example.startprojeect.common.Helper
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import com.example.startprojeect.data.product
import com.example.startprojeect.data.stock
import com.example.startprojeect.databinding.FragmentHomeBinding
import com.example.startprojeect.databinding.FragmentHomeListBinding
import com.example.startprojeect.domain.AuthViewModel
import com.example.startprojeect.domain.CategoryViewModel
import com.example.startprojeect.domain.ProductViewModel
import com.example.startprojeect.domain.StateViewModel
import com.example.startprojeect.domain.StockViewModel
import com.example.startprojeect.presentation.adapters.StockAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout
import com.yarolegovich.slidingrootnav.transform.RootTransformation
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private lateinit var stateViewModel: StateViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var stockViewModel: StockViewModel
    private lateinit var authViewModel: AuthViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.apply {
            statusBarColor = resources.getColor(R.color.test_back)
            navigationBarColor = resources.getColor(R.color.white)
            setBackgroundDrawableResource(R.drawable.for_menu_test)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListBinding.inflate(inflater,container,false)
        stateViewModel = ViewModelProvider(requireActivity())[StateViewModel::class.java]
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        stockViewModel = ViewModelProvider(requireActivity())[StockViewModel::class.java]
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        val ready = requireActivity().findViewById<AppCompatTextView>(R.id.textReady)
        ready.isVisible = false
        val mainText = requireActivity().findViewById<AppCompatTextView>(R.id.texttexttext)
        mainText.isVisible = false
        val shoppingBag =requireActivity().findViewById<FloatingActionButton>(R.id.floatbtn)

        stateViewModel.upperMenuVIsibility(false)
        stateViewModel.hamburgerVisibility(false)
        stateViewModel.shoppingVisibility(false)
        stateViewModel.menuVisibility(true)
        stateViewModel.cardVisibility(false)
        stateViewModel.heartVisibility(false)
        stateViewModel.arrowVisibility(false)

        //Open Side Screen
        val openMenuuu = SlidingRootNavBuilder(requireActivity())
            .withMenuLayout(R.layout.menu_side_layout)
            .withRootViewElevation(40)
            .withDragDistance(210)
            .withRootViewScale(0.65f)
            .addRootTransformation { degree, rootView ->
                if (degree >= 0.5){
                    rootView?.apply {
                        rotation = -5f
                        background = ContextCompat.getDrawable( requireContext(),R.drawable.for_menu_test)
                    }
                }else{
                    rootView?.apply {
                        rotation = 0f
                        background = ContextCompat.getDrawable( requireContext(),R.drawable.color_dor_backckksk)
                    }
                }
            }
            .inject()

        val bagggggg = requireActivity().findViewById<LinearLayout>(R.id.shopBag)
        bagggggg.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shoppingBagFragment)
            openMenuuu.closeMenu()
        }
        val logoutt = requireActivity().findViewById<LinearLayout>(R.id.logOut)
        logoutt.setOnClickListener{
            lifecycleScope.launch {
                try {
                    authViewModel.logOut()
                }catch (e:Exception){
                    Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
                }
            }.invokeOnCompletion {
                findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
            }

        }
        binding.ham.setOnClickListener {
            if (openMenuuu.isMenuOpened){
                openMenuuu.closeMenu()
            }else{
                openMenuuu.openMenu()
            }
        }

        shoppingBag.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_shoppingBagFragment)
        }
        binding.shopBagFromToolbar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shoppingBagFragment)

        }

        var categorysss: List<category>? = null
        lifecycleScope.launch {
            try {
                categorysss = categoryViewModel.getCategoryes()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (categorysss != null){
                binding.listCategory.adapter = MyHomeRecyclerViewAdapter(categorysss!!)
                binding.listCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }
        }

        var popular: List<product>? = null
        lifecycleScope.launch {
            try {
               popular = productViewModel.getPopular()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if (popular != null){
                binding.listPopular.adapter = SneekersAdapter(popular!!)
                binding.listPopular.layoutManager = GridLayoutManager(requireContext(),GridLayoutManager.VERTICAL)
            }
        }
        var stock: List<stock>? = null
        lifecycleScope.launch {
            try {
                stock = stockViewModel.getAllStock()
            }catch (e:Exception){
                Helper.Alert(requireContext(),e.cause.toString(),e.message.toString())
            }
        }.invokeOnCompletion {
            if(stock != null){
                binding.listStock.adapter = StockAdapter(stock!!)
                binding.listStock.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            }
        }


        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}