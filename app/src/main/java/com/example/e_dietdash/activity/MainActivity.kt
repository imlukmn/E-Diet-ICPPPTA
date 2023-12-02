package com.example.e_dietdash.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_dietdash.R
import com.example.e_dietdash.databinding.ActivityMainBinding
import com.example.e_dietdash.ui.ViewPagerAdapter
import com.example.e_dietdash.ui.diet.DietFragment
import com.example.e_dietdash.ui.education.EducationFragment
import com.example.e_dietdash.ui.home.HomeFragment
import com.example.e_dietdash.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupTab()

    }

    private fun setupTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "")
        adapter.addFragment(EducationFragment(), "")
        adapter.addFragment(DietFragment(), "")
        adapter.addFragment(ProfileFragment(), "")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_education)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_diet)
        binding.tabs.getTabAt(3)!!.setIcon(R.drawable.ic_profile)
    }
}