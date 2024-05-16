/**
 * This class created for Home  adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.example.startprojeect.data.products
import com.example.startprojeect.databinding.FragmentFavoriteBinding

import com.squareup.picasso.Picasso


class SneekersAdapter(
    private val values: List<products>
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
        if (item.is_best_seller == true){
            holder.typeBestSeller.isVisible = true
            holder.typeBestSeller.text = "Best seller"
        }

        Picasso.get().load(item.photo).into(holder.sneekImage)
        holder.price.text = item.cost.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        val typeBestSeller: AppCompatTextView = binding.typeBestSeller
        var sneekImage: AppCompatImageView = binding.imageSneekers
        val price: AppCompatTextView = binding.priceForSneekers
    }

}