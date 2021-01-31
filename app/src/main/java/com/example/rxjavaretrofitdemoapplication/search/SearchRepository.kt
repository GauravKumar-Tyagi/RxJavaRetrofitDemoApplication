package com.example.rxjavaretrofitdemoapplication.search

import com.example.rxjavaretrofitdemoapplication.apiHelper.NetworkService
import com.example.rxjavaretrofitdemoapplication.apiHelper.Networking
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.request.SignInRequest
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.SigninResponse
import com.example.rxjavaretrofitdemoapplication.search.response.Photo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

object SearchRepository {

    const val API_KEY = "ed3718658a013cfc67ea9b32d4bc5677"
    val networkService : NetworkService = Networking.create("https://api.flickr.com/")

    fun search(query: String): Observable<Photo> {
        return networkService.search(API_KEY, query)
    }


}