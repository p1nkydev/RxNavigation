package com.pinkydev.rxfragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.AppNavigator
import kotlinx.android.synthetic.main.fragment_letter.view.*
import org.koin.android.ext.android.inject

abstract class BaseLetterFragment : Fragment() {
    protected val navigator by inject<AppNavigator>()

    abstract val letter: String

    protected open fun onButtonBackPressed() {
        navigator.navigateBack()
    }

    protected open fun onButtonNextPressed() {
        navigator.navigateNext()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_letter, container, false).apply {
        tvFragmentLetter.text = letter
        btnBack.setOnClickListener { onButtonBackPressed() }
        btnNext.setOnClickListener { onButtonNextPressed() }
    }
}