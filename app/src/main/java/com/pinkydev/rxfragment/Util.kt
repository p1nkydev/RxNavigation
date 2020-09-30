package com.pinkydev.rxfragment

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.navigation.NavigationFlow
import com.pinkydev.rxfragment.navigation.tab.FirstTabFlow
import com.pinkydev.rxfragment.navigation.tab.SecondTabFlow
import com.pinkydev.rxfragment.navigation.tab.ThirdTabFlow


fun transparentStatusBar(activity: Activity, isTransparent: Boolean, fullscreen: Boolean) {
    if (isTransparent) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // FOR TRANSPARENT NAVIGATION BAR
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        activity.window.statusBarColor = Color.TRANSPARENT
    } else {
        if (fullscreen) {
            val decorView: View = activity.window.decorView
            val uiOptions: Int = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.white)
        }
    }
}

fun flowFactory(frm: FragmentManager, position: Int): NavigationFlow = when (position) {
    0 -> FirstTabFlow(frm)
    1 -> SecondTabFlow(frm)
    2 -> ThirdTabFlow(frm)
    else -> error("No such tab")
}