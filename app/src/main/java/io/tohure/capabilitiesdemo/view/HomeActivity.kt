package io.tohure.capabilitiesdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = host.navController

        val appBar = AppBarConfiguration(
            setOf(
                R.id.productsFragment,
                R.id.orderFragment,
                R.id.accountFragment
            )
        )

        setupActionBarWithNavController(navController, appBar)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

}