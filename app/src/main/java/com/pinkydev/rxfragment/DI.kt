package com.pinkydev.rxfragment

import androidx.fragment.app.FragmentManager
import com.pinkydev.rxfragment.local.LocalSource
import com.pinkydev.rxfragment.main.MainViewModel
import com.pinkydev.rxfragment.navigation.AppNavigator
import com.pinkydev.rxfragment.remote.RemoteSource
import com.pinkydev.rxfragment.repository.MainRepository
import com.pinkydev.rxfragment.ui.dashboard.FragmentF
import com.pinkydev.rxfragment.ui.dashboard.FragmentG
import com.pinkydev.rxfragment.ui.home.*
import com.pinkydev.rxfragment.ui.notifications.FragmentK
import com.pinkydev.rxfragment.ui.notifications.FragmentN
import com.pinkydev.rxfragment.ui.notifications.ViewModelN
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {

    single { (fgm: FragmentManager) -> AppNavigator(fgm) }

    factory { LocalSource() }
    factory { RemoteSource() }

    single { MainRepository(local = get(), remote = get()) }

    viewModel { ViewModelN(repository = get()) }
    viewModel { ViewModelA(repository = get()) }
    viewModel { MainViewModel(repository = get()) }

    factory(named("FIRST_A")) { FragmentA(R.color.white, isFirstTab = true) }
    factory(named("SECOND_A")) { FragmentA(R.color.blue, isFirstTab = false) }

    factory { FragmentB() }
    factory { FragmentC() }
    factory { FragmentD() }
    factory { FragmentE() }
    factory { FragmentF() }
    factory { FragmentG() }
    factory { FragmentN() }
    factory { FragmentK() }
}
