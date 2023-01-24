package com.sadhin.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.sadhin.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding:ActivityMainBinding?=null
    private val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navControl()
        bottomNav()
    }

    private fun navControl(){
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }
    private fun bottomNav(){
        binding.bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home->{
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.bookmark -> {
                    navController.navigate(R.id.bookMarkFragment)
                    true
                }
                else-> false
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}