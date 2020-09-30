package com.pinkydev.rxfragment.ui.notifications

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.AppNavigator
import kotlinx.android.synthetic.main.fragment_k.*
import org.koin.android.ext.android.inject

class FragmentK : Fragment(R.layout.fragment_k) {
    private val navigator by inject<AppNavigator>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack.setOnClickListener { navigator.navigateBack() }
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
    }
}