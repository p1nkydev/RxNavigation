package com.pinkydev.rxfragment.remote

import com.pinkydev.rxfragment.model.HotData
import com.pinkydev.rxfragment.model.User
import com.pinkydev.rxfragment.model.UserDetails
import com.pinkydev.rxfragment.model.UserGeneral
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RemoteSource {

    fun getUser(): Single<UserGeneral> =
        Single.just(UserGeneral("Name", 25))

    fun getUserDetails(): Single<UserDetails> =
        Single.just(UserDetails("dn", true))

    fun getUnsortedUsers(): Single<List<User>> =
        Single.just(
            listOf(
                User(name = "name 3", age = 30, city = "dn", isNewUser = true),
                User(name = "name 1", age = 10, city = "zp", isNewUser = false),
                User(name = "name 2", age = 20, city = "kr", isNewUser = true),
            )
        )

    fun observeHotData(): Observable<HotData> = Observable
        .interval(0, 1, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.computation())
        .map { HotData(it, "type $it") }
        .take(100)

    fun getGreetingFor(user: User): Observable<String> = Observable.just("Cool greeting for ${user.name}")

}