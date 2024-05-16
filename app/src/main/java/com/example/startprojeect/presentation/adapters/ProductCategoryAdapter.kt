package com.example.startprojeect.presentation.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.startprojeect.R
import com.example.startprojeect.data.products
import com.example.startprojeect.databinding.PopularForHomeBinding
import com.example.startprojeect.domain.ProductViewModel
import com.squareup.picasso.Picasso

class ProductCategoryAdapter(
    private val values: List<products>,
    private val requierActivity: FragmentActivity
) : RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder>() {

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
        holder.card.setOnClickListener {
            holder.productViewModel.currentSelectedProduct = item
            Navigation.findNavController(holder.card).navigate(R.id.action_categoryPageFragment_to_sneekViewDetailsFragment)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PopularForHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val productViewModel = ViewModelProvider(requierActivity)[ProductViewModel::class.java]
        val typeBestSeller: AppCompatTextView = binding.typeBestSeller
        var sneekImage: AppCompatImageView = binding.imageSneekers
        val price: AppCompatTextView = binding.priceForSneekers
        val card: LinearLayout = binding.oneFavorite
    }
}
