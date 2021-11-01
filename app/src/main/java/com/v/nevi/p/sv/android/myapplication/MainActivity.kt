package com.v.nevi.p.sv.android.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.v.nevi.p.sv.android.myapplication.HistoryFragment.RecreateHistoryFragment


class MainActivity : AppCompatActivity(),CounterCallback,HistoryFragment.DeleteCallback,
    RecreateHistoryFragment {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        if(callingActivity!=null){
            navController.navigate(R.id.HistoryFragment)
        }
    }

    override fun startCounter() {
        navController.navigate(R.id.counter_activity)
    }

    override fun startDeleteDialog() {
        val dialogDelete=DeleteHistoryDialogFragment()
        dialogDelete.show(supportFragmentManager,null)
    }

    override fun recreateHistoryFragment() {
        recreate()
    }
}