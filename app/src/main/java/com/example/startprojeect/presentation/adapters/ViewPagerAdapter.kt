/**
 * This class created for ViewPager adapter
 * Author Korovkina Valentina
 * Created date 09.05.2024
 * */
package com.example.startprojeect.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.startprojeect.presentation.onboarding.FirstFragment
import com.example.startprojeect.presentation.onboarding.SecondFragment
import com.example.startprojeect.presentation.onboarding.ThirdFragment

class ViewPagerAdapter(
    requireActivity: FragmentActivity,
    lifecycle: Lifecycle
    ): FragmentStateAdapter(requireActivity.supportFragmentManager,lifecycle) {

    private val queue = ArrayList<Fragment>().apply {
        add(FirstFragment())
        add(SecondFragment())
        add(ThirdFragment())
    }
    override fun getItemCount(): Int {
        return queue.size
    }

    override fun createFragment(position: Int): Fragment {
        return  queue[position]
    }
}