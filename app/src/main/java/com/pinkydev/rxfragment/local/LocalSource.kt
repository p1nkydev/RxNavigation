package com.pinkydev.rxfragment.local

import com.pinkydev.rxfragment.model.HotData
import com.pinkydev.rxfragment.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class LocalSource {

    private val userSubject = BehaviorSubject.create<User>()

    fun observeUser(): Observable<User> = userSubject

    fun saveUser(user: User): Completable =
        Completable.fromAction {
            userSubject.onNext(user)
        }

    fun observeHotData(): Observable<HotData> = Observable
        .interval(0, 1, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.computation())
        .map { HotData(it, "type $it") }
        .take(100)

}