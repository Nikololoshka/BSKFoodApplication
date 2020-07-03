package com.vereshchagin.nikolay.pepegafood

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vereshchagin.nikolay.pepegafood.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Главная активность приложения.
 */
class MainActivity : AppCompatActivity() {

    companion object {
       val TAG = "MainActivityLog"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // конфигурация навигации
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_catalog, R.id.nav_basket, R.id.nav_map, R.id.nav_profile)
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // для главной страницы уникальный ActionBar
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d(TAG, "onCreate: destination - " + destination.label)
            setAddressActionBar(destination.id == R.id.nav_home)
        }

        // TODO("Test badge)
        val badge = binding.navView.getOrCreateBadge(R.id.nav_basket)
        badge.maxCharacterCount = 3
        badge.number = 100
    }

    private fun setAddressActionBar(enable: Boolean) {
        if (enable) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        } else {
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }
    }
}