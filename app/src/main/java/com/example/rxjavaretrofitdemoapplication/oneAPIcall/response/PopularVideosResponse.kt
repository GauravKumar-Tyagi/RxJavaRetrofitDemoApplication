package com.example.rxjavaretrofitdemoapplication.oneAPIcall.response

import com.google.gson.annotations.SerializedName

data class PopularVideosResponse(
                                 @SerializedName("message")
                                 val message: String = "",
                                 @SerializedName("status")
                                 val status: String = "")