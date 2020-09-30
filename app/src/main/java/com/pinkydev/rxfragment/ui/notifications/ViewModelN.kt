package com.pinkydev.rxfragment.ui.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pinkydev.rxfragment.model.User
import com.pinkydev.rxfragment.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ViewModelN(private val repository: MainRepository) : ViewModel() {
    private val disp = CompositeDisposable()
    val subject = BehaviorSubject.create<User>()

    init {
        disp.add(
            repository
                .exampleGreeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    Log.e("RX_IS_SHIT", "error $it")
                })
        )
    }

    fun getUser() {
        disp.addAll(
            repository.updateUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("RX_IS_SHIT", "user is updated")
                }, {
                    Log.e("RX_IS_SHIT", "error")
                }),

            repository.observeUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user -> subject.onNext(user) }

        )
    }

    fun listenHotData() {
        disp.add(
            repository.mergeThreads()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.e("RX_IS_SHIT", "Merged hot data: $it")
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disp.dispose()
    }

}