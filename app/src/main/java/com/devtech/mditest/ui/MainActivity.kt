package com.devtech.mditest.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devtech.mditest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> navView.visibility = View.VISIBLE
                R.id.navigation_cart -> navView.visibility = View.VISIBLE
                R.id.navigation_favorite -> navView.visibility = View.VISIBLE
                R.id.navigation_location -> navView.visibility = View.VISIBLE
                R.id.navigation_person -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }
}