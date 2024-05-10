///**
// * This class created for ShoppingBag adapter
// * Author Korovkina Valentina
// * Created date 09.05.2024
// * */
//package com.example.startprojeect.presentation.adapters
//
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.TextView
//
//import com.example.startprojeect.databinding.FragmentShoppingBagBinding
//
//
//class MyshopBagRecyclerViewAdapter(
//    private val values: List<PlaceholderItem>
//) : RecyclerView.Adapter<MyshopBagRecyclerViewAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        return ViewHolder(
//            FragmentShoppingBagBinding.inflate(
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
//    inner class ViewHolder(binding: FragmentShoppingBagBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        val idView: TextView = binding.itemNumber
//        val contentView: TextView = binding.content
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
//    }
//
//}