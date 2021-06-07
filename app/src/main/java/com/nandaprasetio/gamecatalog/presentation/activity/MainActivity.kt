package com.nandaprasetio.gamecatalog.presentation.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.activity.BaseActivity
import com.nandaprasetio.gamecatalog.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding?.also {
            it.bottomNavigationViewNavHost.setupWithNavController(
                (supportFragmentManager.findFragmentById(it.fragmentNavHost.id) as NavHostFragment).navController
            )
        }
    }

    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        viewBinding?.also {
            it.bottomNavigationViewNavHost.apply {
                if (this.selectedItemId != R.id.nav_graph_home) {
                    this.selectedItemId = R.id.nav_graph_home
                } else {
                    this@MainActivity.finish()
                }
            }
        }
    }
}