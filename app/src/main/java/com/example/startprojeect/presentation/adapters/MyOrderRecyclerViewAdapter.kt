package com.example.startprojeect.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.startprojeect.data.orders

import com.example.startprojeect.databinding.FragmentOrderBinding


class MyOrderRecyclerViewAdapter(
    private val values: List<orders>
) : RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentOrderBinding) : RecyclerView.ViewHolder(binding.root) {
//        val idView: TextView = binding.itemNumber
//        val contentView: TextView = binding.content

    }

}