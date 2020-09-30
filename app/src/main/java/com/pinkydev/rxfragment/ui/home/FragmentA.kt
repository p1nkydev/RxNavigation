package com.pinkydev.rxfragment.ui.home

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.pinkydev.rxfragment.base.BaseLetterFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentA : BaseLetterFragment() {

    companion object {
        operator fun invoke(@ColorRes statusColor: Int, isFirstTab: Boolean) = FragmentA().apply {
            arguments = bundleOf(
                "status" to statusColor,
                "tab" to isFirstTab
            )
        }
    }

    private val viewModel by viewModel<ViewModelA>()

    override val letter: String
        get() = "A"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val statusColor = arguments?.get("status") as? Int ?: error("no color")
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), statusColor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isFromFirst = arguments?.get("tab") as? Boolean ?: error("no color")

        if (isFromFirst) {
            viewModel.greetUser()
        } else {
            viewModel.getSortedUsers()
        }
    }

    override fun onButtonBackPressed() {
        // ignore
    }

    override fun onButtonNextPressed() {
        navigator.navigateNext()
    }
}