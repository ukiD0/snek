/**
 * This class created for Favorites sneekers adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.startprojeect.data.products

import com.example.startprojeect.databinding.FragmentFavoriteBinding
import com.squareup.picasso.Picasso


class MyFavoriteRecyclerViewAdapter(
    private val values2: List<products>
) : RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder>() {

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
        val item2 = values2[position]
        Picasso.get().load(item2.photo).into(holder.image)
        holder.name.text = item2.title
        holder.price.text = item2.cost.toString()
    }

    override fun getItemCount(): Int = values2.size

    inner class ViewHolder(binding: FragmentFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: AppCompatImageView = binding.imageSneekers
        val name: AppCompatTextView = binding.nameSneekers
        val price: AppCompatTextView = binding.priceForSneekers

    }

}