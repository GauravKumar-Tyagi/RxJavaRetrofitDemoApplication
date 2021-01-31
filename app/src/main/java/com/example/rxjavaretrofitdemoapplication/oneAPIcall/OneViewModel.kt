package com.example.rxjavaretrofitdemoapplication.oneAPIcall

import androidx.lifecycle.MutableLiveData
import com.example.rxjavaretrofitdemoapplication.base.MyViewModel
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.request.SignInRequest
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.SigninResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class OneViewModel  : MyViewModel() {

    val response : MutableLiveData<SigninResponse> = MutableLiveData()

    var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun clearCompositeDisposable() {
        onCleared()
    }


    fun getLogin(request : SignInRequest) {
        isLoading.postValue(true)
        compositeDisposable.addAll(
            RepositoryImpl.doSigninCall(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        response.postValue(it)
                        isLoading.postValue(false)
                    },
                    {
                        // on error
                        isLoading.postValue(false)
                        apiError.postValue(it?.message ?: "NULL") // Throwable messahe
                    }
                )

        )


    }




}