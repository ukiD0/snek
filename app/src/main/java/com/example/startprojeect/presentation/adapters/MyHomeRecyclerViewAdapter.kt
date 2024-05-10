///**
// * This class created for Home  adapter
// * Author Korovkina Valentina
// * Created date 09.05.2024
// * */
//package com.example.startprojeect.presentation
//
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.example.startprojeect.R
//
//import com.example.startprojeect.presentation.placeholder.PlaceholderContent.PlaceholderItem
//import com.example.startprojeect.databinding.FragmentHomeBinding
//
///**
// * [RecyclerView.Adapter] that can display a [PlaceholderItem].
// * TODO: Replace the implementation with code for your data type.
// */
//class MyHomeRecyclerViewAdapter(
//    private val values: List<PlaceholderItem>
//) : RecyclerView.Adapter<MyHomeRecyclerViewAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        return ViewHolder(
//            FragmentHomeBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = values[position]
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
//    }
//
//    override fun getItemCount(): Int = values.size
//
//    inner class ViewHolder(binding: FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root) {
//        val idView: TextView = binding.itemNumber
//        val contentView: TextView = binding.content
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
//    }
//
//}