package com.pinkydev.rxfragment.ui.dashboard

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.base.BaseLetterFragment

class FragmentG : BaseLetterFragment() {
    override val letter: String
        get() = "G"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
    }
}
