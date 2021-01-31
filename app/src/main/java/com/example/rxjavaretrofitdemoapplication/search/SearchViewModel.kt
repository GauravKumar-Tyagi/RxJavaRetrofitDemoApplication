package com.example.rxjavaretrofitdemoapplication.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rxjavaretrofitdemoapplication.base.MyViewModel
import com.example.rxjavaretrofitdemoapplication.search.helper.SearchNavigationListener
import com.example.rxjavaretrofitdemoapplication.search.response.Photo
import com.example.rxjavaretrofitdemoapplication.search.response.PhotoX
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchViewModel : MyViewModel() {



    var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun clearCompositeDisposable() {
        onCleared()
    }





    val response : MutableLiveData<List<PhotoX>> = MutableLiveData<List<PhotoX>>()

    fun navigationSearchListener(): SearchNavigationListener = searchNavigationListener

    val searchNavigationListener: SearchNavigationListener = object :
        SearchNavigationListener {
        override fun onSearchQuery(query: Observable<String>) {
            compositeDisposable.addAll(
                    query.debounce(1000, TimeUnit.MILLISECONDS)
                            .filter {
                                if (it.isEmpty()) {
                                    //textViewResult.setText("");
                                    // No Result
                                    Log.e("~~~!!@@@", "Empty :: "+"Empty Set,"+it+",")
                                    false;
                                } else {
                                    Log.e("~~~!!@@@", "NOT Empty :: "+"NOT Empty Set,"+it+",")
                                    true;
                                }
                            }
                            .distinctUntilChanged()
                            .switchMap {
                                isLoading.postValue(true)
                                getListBySearch_Filter_NetworkCall(it)
                                        ?.doOnError {
                                            //handleNetworkError(it)
                                            Log.e("~~~!!@@@", "Error 1 :: "+"Error 1 :: "+it?.message)
                                        }?.onErrorReturn {
                                            Log.e("~~~!!@@@", "Empty Set 1 :: "+"Return Empty Set 1")
                                            Photo(null,"")
                                        }

                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { result ->
                                        //textViewResult.setText(it);
                                        // Show Result
                                        Log.e("~~~!!@@@", "Result :: "+result?.photos?.photo?.size)
                                        if(result == null || result?.photos == null || result?.photos?.photo == null || result?.photos?.photo?.size == 0){
                                            var list: ArrayList<PhotoX>? = ArrayList()
                                            response.postValue(list)
                                        }else{
                                            var list: List<PhotoX>? = result?.photos?.photo
                                            response.postValue(list)
                                        }
                                        isLoading.postValue(false)
                                    }, {
                                t: Throwable? ->
                                Log.e("~~~!!@@@", "Error 2 :: "+"Failed to get search results :: "+t?.message)
                                var list: ArrayList<PhotoX>? = ArrayList()
                                response.postValue(list)
                                isLoading.postValue(false)
                            }
                            )
            )
        }
    }

    fun getListBySearch_Filter_NetworkCall(
            query: String
    ): Observable<Photo>? {
        return SearchRepository.search(query)
    }




}