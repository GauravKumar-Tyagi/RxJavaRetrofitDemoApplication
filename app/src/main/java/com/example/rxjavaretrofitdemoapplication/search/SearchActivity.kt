package com.example.rxjavaretrofitdemoapplication.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rxjavaretrofitdemoapplication.R
import com.example.rxjavaretrofitdemoapplication.base.BaseActivity
import com.example.rxjavaretrofitdemoapplication.search.adapter.PhotoAdapter
import com.example.rxjavaretrofitdemoapplication.search.helper.RxSearchObservable
import com.example.rxjavaretrofitdemoapplication.search.helper.SearchNavigationListener
import com.example.rxjavaretrofitdemoapplication.search.response.PhotoX
import kotlinx.android.synthetic.main.activity_search.*
import java.util.ArrayList


class SearchActivity : BaseActivity() {

    private val mPhotoXItems: ArrayList<PhotoX> = ArrayList()
    var mViewModel: SearchViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
        setUI()
        click()
    }

    fun init(){
        mViewModel= ViewModelProviders.of(this).get(SearchViewModel::class.java)
        attachObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.clearCompositeDisposable()
    }


    fun attachObservers()
    {
        mViewModel?.apiError?.observe(this, Observer {
            it?.let {
                showSnackBar(it,idParent)
            }
        })

        mViewModel?.isLoading?.observe(this, Observer {
            it?.let {
                //showLoading(it)
                showProgress(it)
            }
        })


        mViewModel?.response?.observe(this, Observer {
            //Log.e("~~~~!~!~!~","Token :: "+it?.title)
            mPhotoXItems?.clear()
            mPhotoXItems?.addAll(it)
            photosView?.adapter?.notifyDataSetChanged()
        })

    }



    fun click(){
        mViewModel?.navigationSearchListener()?.let { searchFilter(idSearch, it) }
    }

    fun searchFilter(view: AppCompatEditText, listener: SearchNavigationListener) {
        var query = RxSearchObservable.fromView(view)
        listener.onSearchQuery(query)
    }


    fun setUI(){
        photosView?.adapter= PhotoAdapter(mPhotoXItems){ item->
            Log.e("~~~~~~!!!@!~~~!@!~"," photo Item Clickes :: "+item?.title)
            photosView?.adapter?.notifyDataSetChanged()
        }
    }

    fun showProgress(flag : Boolean){
        if(flag)
            idProgress?.visibility = View.VISIBLE
        else
            idProgress?.visibility = View.GONE
    }

}