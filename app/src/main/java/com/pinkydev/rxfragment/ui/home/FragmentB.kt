package com.pinkydev.rxfragment.ui.home

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.base.BaseLetterFragment

class FragmentB : BaseLetterFragment() {
    override val letter: String
        get() = "B"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
    }
}