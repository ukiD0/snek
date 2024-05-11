/**
 * This class created for Home  adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.startprojeect.R
import com.example.startprojeect.data.category
import com.example.startprojeect.data.product
import com.example.startprojeect.databinding.FragmentFavoriteBinding

import com.example.startprojeect.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso


class SneekersAdapter(
    private val values: List<product>
) : RecyclerView.Adapter<SneekersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.typeBestSeller.text = "Best Seller"
        Picasso.get().load(item.image).into(holder.sneekImage)
        holder.price.text = item.price.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        val typeBestSeller: AppCompatTextView = binding.typeBestSeller
        var sneekImage: AppCompatImageView = binding.imageSneekers
        val price: AppCompatTextView = binding.priceForSneekers
    }

}