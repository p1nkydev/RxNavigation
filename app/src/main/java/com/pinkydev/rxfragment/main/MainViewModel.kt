package com.pinkydev.rxfragment.main

import androidx.lifecycle.ViewModel
import com.pinkydev.rxfragment.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    // should be added to composite disposable and dispose on clear
    fun startInterval() {
        repository
            .startInterval()
            .subscribe()
    }

    // should be added to composite disposable and dispose on clear
    fun stopInterval() {
        repository
            .stopInterval()
            .subscribe()
    }
}