package ui.smartpro.a22byte.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ui.smartpro.a22byte.R
import ui.smartpro.a22byte.databinding.ActivityMainBinding
import ui.smartpro.a22byte.ui.main.MainViewModel
import ui.smartpro.common.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val mainViewModel: MainViewModel by viewModels()

    override fun onViewReady(savedInstanceState: Bundle?) {
        super.onViewReady(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.today_news);
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        savedInstanceState?.let {
            mainViewModel.hideErrorToast()
        }
    }

    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun setupBottomNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.favouriteFragment
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}