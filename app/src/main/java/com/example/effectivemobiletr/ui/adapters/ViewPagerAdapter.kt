package com.example.effectivemobiletr.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.effectivemobiletr.ui.fragments.viewPager.BrandsFragment
import com.example.effectivemobiletr.ui.fragments.viewPager.ProductsFragment


class ViewPagerAdapter(frag: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(frag, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> {
                ProductsFragment()
            }
            1 -> {
                BrandsFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}