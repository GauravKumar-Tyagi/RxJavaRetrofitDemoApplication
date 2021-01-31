package com.example.rxjavaretrofitdemoapplication.search.response

import java.util.*

data class Photo(
    var photos: Photos?=null,
    var stat: String
)

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoX>,
    val total: String
)

data class PhotoX(
    val farm: Int,
    val height_c: Int,
    val height_l: Int,
    val height_m: Int,
    val height_n: Int,
    val height_o: Int,
    val height_q: Int,
    val height_s: Int,
    val height_sq: Int,
    val height_t: Int,
    val height_z: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val url_c: String,
    val url_l: String,
    val url_m: String,
    val url_n: String,
    val url_o: String,
    val url_q: String,
    val url_s: String,
    val url_sq: String,
    val url_t: String,
    val url_z: String,
    val width_c: Int,
    val width_l: Int,
    val width_m: Int,
    val width_n: Int,
    val width_o: Int,
    val width_q: Int,
    val width_s: Int,
    val width_sq: Int,
    val width_t: Int,
    val width_z: Int
)