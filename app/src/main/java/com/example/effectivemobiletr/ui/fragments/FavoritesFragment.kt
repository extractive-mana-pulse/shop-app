package com.example.effectivemobiletr.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletr.databinding.FragmentFavoritesBinding
import com.example.effectivemobiletr.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesFragment : Fragment() {

    private lateinit var binding : FragmentFavoritesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Товары"
                }
                1 -> {
                    tab.text = "Бренды"
                }
            }
        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}