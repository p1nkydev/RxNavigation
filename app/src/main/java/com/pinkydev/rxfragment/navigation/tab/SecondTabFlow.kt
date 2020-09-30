package com.pinkydev.rxfragment.navigation.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.NavigationFlow
import com.pinkydev.rxfragment.ui.dashboard.FragmentF
import com.pinkydev.rxfragment.ui.dashboard.FragmentG
import com.pinkydev.rxfragment.ui.home.FragmentA
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

class SecondTabFlow(private val frm: FragmentManager) :
    NavigationFlow, KoinComponent {
    private var currentFragment: Fragment = get<FragmentA>(named("SECOND_A"))

    override fun navigateCurrent() {
        replaceFragment(currentFragment)
    }

    override fun navigateNext() {
        val next = when(currentFragment) {
            is FragmentA -> get<FragmentF>()
            else -> get<FragmentG>()
        }
        replaceFragment(next)
    }

    override fun navigateBack() {
        when (currentFragment) {
            is FragmentA -> {
                // ignore
            }
            is FragmentF -> {
                replaceFragment(get<FragmentA>(named("SECOND_A")))
            }
            is FragmentG -> {
                replaceFragment(get<FragmentF>())
            }
        }
    }

    override fun navigateExit() {
        replaceFragment(get<FragmentA>(named("SECOND_A")))
    }

    private fun replaceFragment(fragment: Fragment) {
        frm.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        currentFragment = fragment
    }

}