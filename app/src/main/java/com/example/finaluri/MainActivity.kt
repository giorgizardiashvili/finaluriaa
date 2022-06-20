package com.example.finaluri


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.finaluri.fragments.LeadershipFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_container)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)


        val f = setOf(
            R.id.quizFragment,
            R.id.leadershipFragment

        )
        setupActionBarWithNavController(navController, AppBarConfiguration(f))
        bottomNavView.setupWithNavController(navController)
    }

}