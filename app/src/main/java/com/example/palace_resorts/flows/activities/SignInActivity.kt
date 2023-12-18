package com.example.palace_resorts.flows.activities

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.palace_resorts.R
import com.example.palace_resorts.base.NavigationActivity
import com.example.palace_resorts.databinding.ActivityMainBinding
import com.example.palace_resorts.flows.fragments.SignInFragment
import com.example.palace_resorts.flows.fragments.SignInFragmentDirections

class SignInActivity : NavigationActivity(), SignInFragment.SignInFragmentListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun currentNavController(): NavController = findNavController(R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener {
            onSupportNavigateUp()
        }
    }

    override fun navigateToHome() {
        currentNavController().navigate(
            SignInFragmentDirections.actionToHomeFragment()
        )
    }
}