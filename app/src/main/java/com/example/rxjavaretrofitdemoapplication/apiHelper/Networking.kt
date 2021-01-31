package com.example.rxjavaretrofitdemoapplication.apiHelper


import com.example.rxjavaretrofitdemoapplication.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object Networking {

    private const val TAG = "Networking"
    private const val NETWORK_CALL_TIMEOUT = 40

    fun gsonCreate() : Gson =
        GsonBuilder().setLenient().create()




    fun create(
        baseUrl: String
    ): NetworkService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY   // HttpLoggingInterceptor.Level.BASIC
                            else HttpLoggingInterceptor.Level.NONE
                        })
                    .addInterceptor(requestInterceptor)
                    .connectTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .callTimeout(45, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gsonCreate()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)




    val requestInterceptor: Interceptor = object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {


            val verName: String = BuildConfig.VERSION_NAME
            var originalRequest = chain.request()
            originalRequest = originalRequest.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("App-Ver", verName)
                .build()

            val originalResponse = chain.proceed(originalRequest)
            return originalResponse.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader(
                    "Content-Type",
                    "application/json"
                ) //.body(new ProgressResponseBody(originalResponse.body(), time, callback, timeoutSeconds))
                .build()

        }

    }



}