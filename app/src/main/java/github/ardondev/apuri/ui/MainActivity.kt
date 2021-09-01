package github.ardondev.apuri.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import github.ardondev.apuri.R
import github.ardondev.apuri.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Apuri)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initFlow()
    }

    private fun initFlow() {
        setupNavigation()
    }


    /*
    UI
     */

    private fun setupNavigation() {
        //Set navigation with bottom nav view
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_hostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.mainBottomNavView.setupWithNavController(navController)
    }

}