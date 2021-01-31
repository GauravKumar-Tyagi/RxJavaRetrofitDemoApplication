package com.example.rxjavaretrofitdemoapplication.oneAPIcall.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignInRequest (
    @Expose
    @SerializedName("email")
    var email: String ?= null,

    @Expose
    @SerializedName("password")
    var password: String ?= null
)