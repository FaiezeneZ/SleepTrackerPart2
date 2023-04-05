package com.example.sleeptracker2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sleeptracker2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentSleep: Fragment = SleepFragment()
        val fragmentInfo: Fragment = InformationFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.sleep -> fragment = fragmentSleep
                R.id.information -> fragment = fragmentInfo
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.sleep

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.list_layout, fragment)
        fragmentTransaction.commit()
    }
}