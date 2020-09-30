package com.pinkydev.rxfragment.navigation.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.NavigationFlow
import com.pinkydev.rxfragment.ui.home.*
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

class FirstTabFlow(private val frm: FragmentManager) : NavigationFlow, KoinComponent {

    private var currentFragment: Fragment = get<FragmentA>(named("FIRST_A"))

    override fun navigateCurrent() {
        replaceFragment(currentFragment)
    }

    override fun navigateNext() {
        val next = when (currentFragment) {
            is FragmentA -> get<FragmentB>()
            is FragmentB -> get<FragmentC>()
            is FragmentC -> get<FragmentD>()
            else -> get<FragmentE>()
        }
        replaceFragment(next)
    }

    override fun navigateBack() {
        when (currentFragment) {
            is FragmentA -> {
                //ignore
            }
            is FragmentB -> {
                replaceFragment(get<FragmentA>(named("FIRST_A")))
            }
            is FragmentC -> {
                replaceFragment(get<FragmentB>())
            }
            is FragmentD -> {
                replaceFragment(get<FragmentC>())
            }
            else -> navigateExit()
        }
    }

    override fun navigateExit() {
        replaceFragment(get<FragmentA>(named("FIRST_A")))
    }

    private fun replaceFragment(fragment: Fragment) {
        frm.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        currentFragment = fragment
    }
}