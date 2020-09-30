package com.pinkydev.rxfragment.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.pinkydev.rxfragment.R
import com.pinkydev.rxfragment.navigation.AppNavigator
import com.pinkydev.rxfragment.transparentStatusBar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_n.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentN : Fragment(R.layout.fragment_n) {

    private val navigator by inject<AppNavigator>()

    private val disp = CompositeDisposable()

    private val viewModel by viewModel<ViewModelN>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        viewModel.listenHotData()

        disp.add(viewModel.subject.subscribe {
            Log.e("RX_IS_SHIT", "observing in ui user: $it")
        })

        btnNext.setOnClickListener {
            navigator.navigateNext()
        }
    }

    override fun onResume() {
        super.onResume()
        transparentStatusBar(activity!!, isTransparent = true, fullscreen = false)
    }

    override fun onStop() {
        super.onStop()
        transparentStatusBar(activity!!, isTransparent = false, fullscreen = false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disp.dispose()
    }
}