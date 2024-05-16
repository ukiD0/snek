package com.example.startprojeect.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.startprojeect.R
import com.example.startprojeect.data.cart
import com.example.startprojeect.data.favourite
import com.example.startprojeect.data.products
import com.example.startprojeect.databinding.PopularForHomeBinding
import com.example.startprojeect.domain.AuthViewModel
import com.example.startprojeect.domain.CartViewModel
import com.example.startprojeect.domain.FavoriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PopularAdapter(
    private val values: List<products>,
    private val requireActivity: FragmentActivity,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PopularForHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
            holder.typeBestSeller.text = "Best seller"
            Picasso.get().load(item.photo).into(holder.sneekImage)
            holder.price.text = item.cost.toString()
        holder.heart.setOnClickListener {
            lifecycleScope.launch {
                try {
                    holder.favoriteViewModel.addToFavorite(
                        favourite(
                            id = null,
                            product_id = item.id,
                            user_id = holder.authViewModel.getUserID()
                        )
                    )
                }catch (e:Exception){

                }
            }.invokeOnCompletion {
                holder.heart.setImageResource(R.drawable.hert_for_favorite)
                Toast.makeText(requireActivity,"Товар добавлен в избранное",Toast.LENGTH_LONG).show()
            }
        }
        holder.btnAddCart.setOnClickListener {
            lifecycleScope.launch {
                try {
                    holder.cartViewModel.addProductToCart(
                        cart(
                            id = null,
                            product_id = item.id,
                            user_id = holder.authViewModel.getUserID(),
                            count = 1
                        )
                    )
                }catch (e:Exception){
                }
            }.invokeOnCompletion {
                holder.btnAddCart.setImageResource(R.drawable.corzina)
                holder.btnAddCart.isEnabled = false
                Toast.makeText(requireActivity,"Товар добавлен в коризну",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PopularForHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val favoriteViewModel = ViewModelProvider(requireActivity)[FavoriteViewModel::class.java]
        val authViewModel = ViewModelProvider(requireActivity)[AuthViewModel::class.java]
        val cartViewModel = ViewModelProvider(requireActivity)[CartViewModel::class.java]

        val typeBestSeller: AppCompatTextView = binding.typeBestSeller
        var sneekImage: AppCompatImageView = binding.imageSneekers
        val price: AppCompatTextView = binding.priceForSneekers
        val heart: AppCompatImageView = binding.selectedHert
        val btnAddCart:AppCompatImageView = binding.selectorCart
    }
}
