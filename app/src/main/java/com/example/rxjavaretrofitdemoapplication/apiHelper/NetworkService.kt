package com.example.rxjavaretrofitdemoapplication.apiHelper

import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.PopularVideosResponse
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.response.SigninResponse
import com.example.rxjavaretrofitdemoapplication.search.response.Photo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.Response
import retrofit2.http.*

internal const val URLS = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"

interface NetworkService {

    @FormUrlEncoded
    @POST("api/login")
    //@Headers(RequestHeaders.Key.AUTH_PUBLIC)
    fun doSigninCall(@FieldMap params : Map<String,String?>): Single<SigninResponse>



    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun search(
            @Query("api_key") apiKey: String,
            @Query("text") text: String? = null,
            @Query("extras") extras: String = URLS
    ): Observable<Photo>




    @Headers("Accept: " + "application/json")
    @GET("popular/videos")
    fun getPopularVideos(): Observable<PopularVideosResponse>




}