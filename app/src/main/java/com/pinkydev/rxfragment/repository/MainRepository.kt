package com.pinkydev.rxfragment.repository

import com.pinkydev.rxfragment.local.LocalSource
import com.pinkydev.rxfragment.model.HotData
import com.pinkydev.rxfragment.model.User
import com.pinkydev.rxfragment.remote.RemoteSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// move it somewhere and di later
private val databaseThread: Scheduler = Schedulers.from(Executors.newSingleThreadExecutor())

class MainRepository(private val local: LocalSource, private val remote: RemoteSource) {

    private var intervalDisposable: Disposable? = null
    private var usersStream = createInterval()

    fun updateUser(): Completable =
        remote
            .getUser()
            .flatMap({
                // can use UserGeneral as params for details for example.
                remote.getUserDetails()
            }, { user, details -> user to details })
            .map {
                User(it.first.name, it.first.age, it.second.city, it.second.isNewUser) }
            .retry(2) { it is IOException }
            .subscribeOn(databaseThread)
            .flatMapCompletable { local.saveUser(it) }

    fun observeUser(): Observable<User> = local.observeUser()

    fun getSortedUsersWithInterval(): Observable<List<User>> = usersStream

    fun stopInterval(): Completable = Completable.fromAction {
        intervalDisposable?.dispose()
    }

    fun startInterval(): Completable = Completable.fromAction {
        usersStream = createInterval()
    }

    fun mergeThreads(): Observable<HotData> =
        local.observeHotData().mergeWith(remote.observeHotData())

    private fun createInterval(): Observable<List<User>> = Observable
        .interval(30, TimeUnit.SECONDS)
        .flatMapSingle { remote.getUnsortedUsers() }
        .retry(2) { it is IOException }
        .map { users -> users.sortedBy { it.age } }
        .doOnSubscribe { intervalDisposable = it }


    // is user seen greeting usually stores in back-end
    // if app should store this locally then it would be placed in database and zipped (as below)
    // this case make call for "getGreetingFor" which is mark user as seen greeting on the back-end.
    fun greetUser(): Observable<String> =
        updateUser()
            .andThen(observeUser())
            .filter { it.isNewUser }
            .flatMap { remote.getGreetingFor(it) }




    fun exampleGreeting() = updateUser()
        .andThen(observeUser())
        .zipWith(isGreetedLocally()) { user, isGreet ->
            user to isGreet
        }.map {
            if (it.second) it.first else null
        }

    private fun isGreetedLocally() = Observable.just(false)
}