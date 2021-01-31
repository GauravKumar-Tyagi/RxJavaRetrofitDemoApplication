package com.example.rxjavaretrofitdemoapplication.oneAPIcall

import com.example.rxjavaretrofitdemoapplication.apiHelper.NetworkService
import com.example.rxjavaretrofitdemoapplication.apiHelper.Networking
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.request.SignInRequest
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.PopularVideosResponse
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.SigninResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

object RepositoryImpl {

    val networkService : NetworkService = Networking.create("https://reqres.in/")

    fun doSigninCall(request : SignInRequest) : Single<SigninResponse> {
        val params = HashMap<String, String?>()
        params.put("email", request?.email)
        params.put("password",request?.password)
        return networkService.doSigninCall(params)
    }


}