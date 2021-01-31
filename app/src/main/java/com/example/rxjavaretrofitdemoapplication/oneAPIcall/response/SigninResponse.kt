package com.example.rxjavaretrofitdemoapplication.oneAPIcall.response

import com.google.gson.annotations.SerializedName

data class SigninResponse (
    @SerializedName("token")
    val token: String = ""
)