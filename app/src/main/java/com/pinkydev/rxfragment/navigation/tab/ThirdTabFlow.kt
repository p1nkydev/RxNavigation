package com.pinkydev.rxfragment.navigation.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.NavigationFlow
import com.pinkydev.rxfragment.ui.notifications.FragmentK
import com.pinkydev.rxfragment.ui.notifications.FragmentN
import org.koin.core.KoinComponent
import org.koin.core.get

class ThirdTabFlow(private val frm: FragmentManager) : NavigationFlow, KoinComponent {
    private var currentFragment: Fragment = get<FragmentN>()

    override fun navigateCurrent() {
        replaceFragment(currentFragment)
    }

    override fun navigateNext() {
        replaceFragment(get<FragmentK>())
    }

    override fun navigateBack() {
        when (currentFragment) {
            is FragmentN -> {
                // ignore
            }
            is FragmentK -> {
                replaceFragment(get<FragmentN>())
            }
        }
    }

    override fun navigateExit() {
        replaceFragment(get<FragmentN>())
    }

    private fun replaceFragment(fragment: Fragment) {
        frm.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        currentFragment = fragment
    }
}