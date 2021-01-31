package com.example.rxjavaretrofitdemoapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MyViewModel : ViewModel() {
    var apiError = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()
}