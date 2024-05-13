/**
 * This class created for Notification  adapater
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.startprojeect.data.notifications

import com.example.startprojeect.databinding.FragmentNotificationBinding

class MyNotificationRecyclerViewAdapter(
    private val values: List<notifications>
) : RecyclerView.Adapter<MyNotificationRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.zagolovok.text = item.title
        holder.content.text = item.text
        holder.date.text = item.created_at
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val zagolovok: TextView = binding.zagolovok
        val content: TextView = binding.content
        val date: TextView = binding.date

    }

}