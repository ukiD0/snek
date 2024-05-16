/**
 * This class created for ShoppingBag adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.adapters

import android.util.Log
import android.view.GestureDetector
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.startprojeect.R
import com.example.startprojeect.data.products

import com.example.startprojeect.databinding.FragmentShoppingBagBinding
import com.example.startprojeect.domain.CartViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class MyshopBagRecyclerViewAdapter(
    private val values: List<products>,
    private val requireActivity: FragmentActivity,
    private val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<MyshopBagRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentShoppingBagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.price.text = "₽" + item.cost.toString()
        holder.nameProduct.text = item.title
        Picasso.get().load(item.photo).into(holder.image)
        val gesture = GestureDetector(requireActivity, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                try {
                    if (e2.x - e1!!.x > 0){
                        //right to left
                        holder.blueCard.isVisible = true
                        holder.redCard.isVisible = false
                    }else{
                        //left to right
                        holder.blueCard.isVisible = false
                        holder.redCard.isVisible = true
                    }
                    return true
                }catch (e:Exception){
                    Log.e("test", e.message.toString())
                    return false
                }
            }
        })
        holder.cardSneekers.setOnTouchListener { view, motionEvent ->
            gesture.onTouchEvent(motionEvent)
            true
        }
        holder.redCard.setOnClickListener {
            lifecycleScope.launch {
                try {
                    holder.cartViewModel.deleteByID(item.id.toString())
                }catch (e:Exception){
                }
            }.invokeOnCompletion {
                Navigation.findNavController(holder.redCard).navigate(R.id.action_shoppingBagFragment_to_homeFragment)
                Navigation.findNavController(holder.redCard).navigate(R.id.action_homeFragment_to_shoppingBagFragment)
            }

        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentShoppingBagBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val cartViewModel = ViewModelProvider(requireActivity)[CartViewModel::class.java]
            val image: AppCompatImageView = binding.shoose
            val nameProduct: AppCompatTextView = binding.nameShose
            val price: AppCompatTextView = binding.price
            val blueCard: LinearLayoutCompat = binding.blueCard
            val redCard: LinearLayoutCompat = binding.redCard
            val cardSneekers: LinearLayout = binding.mainCard

    }

}