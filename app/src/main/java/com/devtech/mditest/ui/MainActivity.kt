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

    lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> navigationView.visibility = View.VISIBLE
                R.id.navigation_cart -> navigationView.visibility = View.VISIBLE
                R.id.navigation_favorite -> navigationView.visibility = View.VISIBLE
                R.id.navigation_location -> navigationView.visibility = View.VISIBLE
                R.id.navigation_person -> navigationView.visibility = View.VISIBLE
                else -> navigationView.visibility = View.GONE
            }
        }
    }
}