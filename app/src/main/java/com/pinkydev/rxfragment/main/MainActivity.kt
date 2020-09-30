package com.pinkydev.rxfragment.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.AppNavigator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val navigator by inject<AppNavigator> { parametersOf(supportFragmentManager) }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.changeTab(0)
        viewModel.stopInterval()

        nav_view.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    navigator.changeTab(0)
                    viewModel.stopInterval()
                }
                R.id.navigation_dashboard -> {
                    navigator.changeTab(1)
                    viewModel.startInterval()
                }
                R.id.navigation_notifications -> {
                    navigator.changeTab(2)
                    viewModel.stopInterval()
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    // yes, the app can't hide anymore,
    // it can be easy fixed with boolean flag in flow navigator where now is ignoring
    // btw instagram like navigation would be for free then.
    override fun onBackPressed() {
        navigator.navigateBack()
    }
}