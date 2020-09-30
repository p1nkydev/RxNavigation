package com.pinkydev.rxfragment.navigation

interface NavigationFlow {
    fun navigateCurrent()
    fun navigateNext()
    fun navigateBack()
    fun navigateExit()
}