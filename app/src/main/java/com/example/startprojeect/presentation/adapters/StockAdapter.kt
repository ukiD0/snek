package com.example.startprojeect.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.startprojeect.data.actions
import com.example.startprojeect.databinding.FragmentStockBinding
import com.squareup.picasso.Picasso

class StockAdapter(
    private val values: List<actions>
) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentStockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get().load(item.photo).into(holder.stockImage)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentStockBinding) : RecyclerView.ViewHolder(binding.root) {
        var stockImage: ImageView = binding.image
    }

}