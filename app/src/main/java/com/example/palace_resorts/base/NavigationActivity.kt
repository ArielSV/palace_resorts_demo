package com.example.palace_resorts.base

import androidx.navigation.NavController

abstract class NavigationActivity : BaseFragmentActivity() {

    abstract fun currentNavController(): NavController

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}