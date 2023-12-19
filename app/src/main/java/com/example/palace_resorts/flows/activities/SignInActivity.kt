package com.example.palace_resorts.flows.activities

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.palace_resorts.R
import com.example.palace_resorts.base.NavigationActivity
import com.example.palace_resorts.databinding.ActivityMainBinding
import com.example.palace_resorts.flows.fragments.FavoritesFragment
import com.example.palace_resorts.flows.fragments.FavoritesFragmentDirections
import com.example.palace_resorts.flows.fragments.HomeFragment
import com.example.palace_resorts.flows.fragments.HomeFragmentDirections
import com.example.palace_resorts.flows.fragments.NewsDetailFragment
import com.example.palace_resorts.flows.fragments.SignInFragment
import com.example.palace_resorts.flows.fragments.SignInFragmentDirections
import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.utils.extensionUtils.hide
import com.example.palace_resorts.utils.extensionUtils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : NavigationActivity(), SignInFragment.SignInFragmentListener,
    HomeFragment.HomeFragmentListener, NewsDetailFragment.NewsDetailFragmentListener,
    FavoritesFragment.FavoritesFragmentListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun currentNavController(): NavController = findNavController(R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imageViewBack.setOnClickListener {
            onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return when (currentNavController().currentDestination?.id) {
            R.id.home_fragment -> {
                finish()
                false
            }

            else -> currentNavController().navigateUp()
        }
    }

    override fun navigateToHome() {
        currentNavController().navigate(
            SignInFragmentDirections.actionToHomeFragment()
        )
    }

    override fun showToolBar() {
        binding.toolbar.show()
    }

    override fun navigateToFavoriteDetail(article: Articles) {
        currentNavController().navigate(
            FavoritesFragmentDirections.actionToFavoritesDetailFragment(
                false,
                article
            )
        )
    }

    override fun hideToolbar() {
        binding.toolbar.hide()
    }

    override fun navigateToDetail(article: Articles) {
        currentNavController().navigate(
            HomeFragmentDirections.actionToNewsDetailFragment(true, article)
        )
    }

    override fun navigateToFavorites() {
        currentNavController().navigate(
            HomeFragmentDirections.actionToFavoritesFragment()
        )
    }

    override fun hideToolBar() {
        binding.toolbar.hide()
    }
}