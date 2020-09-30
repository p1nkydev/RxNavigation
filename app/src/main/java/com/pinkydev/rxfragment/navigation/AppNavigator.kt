package com.pinkydev.rxfragment.navigation

import android.util.SparseArray
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.flowFactory

class AppNavigator(private val frm: FragmentManager) {
    private val tabs = SparseArray<NavigationFlow>()

    private var currentNavigator: NavigationFlow? = null


    fun changeTab(position: Int) {
        currentNavigator = tabs[position] ?: flowFactory(frm, position).also {
            tabs[position] = it
        }
        currentNavigator?.navigateCurrent()
    }

    fun navigateNext() {
        currentNavigator?.navigateNext()
    }

    fun navigateBack() {
        currentNavigator?.navigateBack()
    }

    fun navigateExit() {
        currentNavigator?.navigateExit()
    }

}