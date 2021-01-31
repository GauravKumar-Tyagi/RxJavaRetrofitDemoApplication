package com.example.rxjavaretrofitdemoapplication.search.helper

import io.reactivex.rxjava3.core.Observable

interface SearchNavigationListener {
    fun onSearchQuery(query: Observable<String>)
}