package com.example.effectivemobiletr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.effectivemobiletr.R
import com.example.effectivemobiletr.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
//                R.id.home -> { navController.navigate(R.id.homeFragment) }
                R.id.catalog -> { navController.navigate(R.id.catalogFragment) }
//                R.id.basket -> { navController.navigate(R.id.basketFragment) }
//                R.id.discount -> { navController.navigate(R.id.discountFragment) }
                R.id.profile -> { navController.navigate(R.id.profileFragment) }
            }
            true
        }

        val fragmentsToHideBottomNav = setOf( R.id.loginFragment )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in fragmentsToHideBottomNav) {
                binding.bottomNavView.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
            }
        }
    }
}