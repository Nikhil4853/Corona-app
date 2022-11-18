package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.viewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.graphics.drawable.Drawable as Drawable1

public class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#FDF2F2")

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewpager = findViewById<ViewPager2>(R.id.viewPager2)
        val adapter = viewPagerAdapter(supportFragmentManager, lifecycle)
        viewpager.adapter = adapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.badge
                    tab.text = "home"
                    tab.setIcon(R.drawable.home_24)
                }
                1 -> {
                    tab.setIcon(R.drawable.safe_24)
                    tab.text = "Protection"
                }
                2 -> {
                    tab.setIcon(R.drawable.info_24)

                    tab.text = "information"
                }
            }

        }.attach()

    }
}