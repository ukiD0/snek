/**
 * This class created for Home  adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.startprojeect.R
import com.example.startprojeect.data.categories

import com.example.startprojeect.databinding.FragmentHomeBinding
import com.example.startprojeect.domain.CategoryViewModel

class MyHomeRecyclerViewAdapter(
    private val values: List<categories>,
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<MyHomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.categorysss.text = item.title
        holder.categorysss.setOnClickListener {
            if (item.title == "All"){
                holder.categoryViewModel.currentCategoryID = ""
            }else{
                holder.categoryViewModel.currentCategoryID = item.id.toString()
            }
            holder.categoryViewModel.currentCategory = item.title.toString()
            Navigation.findNavController(holder.categorysss).navigate(R.id.action_homeFragment_to_categoryPageFragment)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val categoryViewModel = ViewModelProvider(requireActivity)[CategoryViewModel::class.java]
        val categorysss: TextView = binding.nameCategory

    }

}