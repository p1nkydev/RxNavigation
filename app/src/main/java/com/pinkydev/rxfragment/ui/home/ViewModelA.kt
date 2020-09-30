package com.pinkydev.rxfragment.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pinkydev.rxfragment.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModelA(private val repository: MainRepository) : ViewModel() {

    private val disp = CompositeDisposable()

    // should be added to composite disposable and dispose on clear
    fun getSortedUsers() {
        disp.add(
            repository.getSortedUsersWithInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it -> Log.e("RX_IS_SHIT", it.toString()) }
        )
    }

    // should be added to composite disposable and dispose on clear
    fun greetUser() {
        disp.add(
            repository
                .greetUser()
                .subscribe { Log.e("RX_IS_SHIT", "Displaying greeting: $it") }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disp.dispose()
    }
}