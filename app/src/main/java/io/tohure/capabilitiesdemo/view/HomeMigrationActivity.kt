package io.tohure.capabilitiesdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.ActivityHomeBinding
import io.tohure.capabilitiesdemo.view.ui.theme.CapabilitiesDemoTheme

class HomeMigrationActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(
            ComposeView(this).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    CapabilitiesDemoTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            HomeScreen()
                        }
                    }
                }
            }
        )

        /*val host =
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
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)*/
    }

}