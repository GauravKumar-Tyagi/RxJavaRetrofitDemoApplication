package com.example.rxjavaretrofitdemoapplication.oneAPIcall

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxjavaretrofitdemoapplication.R
import com.example.rxjavaretrofitdemoapplication.base.BaseActivity
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.request.SignInRequest
import kotlinx.android.synthetic.main.activity_one.*

class OneActivity : BaseActivity() {

    var mViewModel: OneViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        init()
        click()
    }

    fun init(){
        mViewModel= ViewModelProviders.of(this).get(OneViewModel::class.java)
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
                showLoading(it)
            }
        })


        mViewModel?.response?.observe(this, Observer {
            Log.e("~~~~!~!~!~","Token :: "+it?.token)
            showSnackBar("Token :: "+it?.token,idParent)
        })

    }


    fun click(){
        idLogin?.setOnClickListener {
            var email = idEmail?.text?.toString()
            var password = idPassword?.text?.toString()
            apiCall(email,password)
        }
    }

    fun apiCall(email : String?, password : String?){
        var request : SignInRequest = SignInRequest()
        /*request.email = "eve.holt@reqres.in"
        request.password = "cityslicka"*/
        request.email = email
        request.password = password
        mViewModel?.getLogin(request)
    }
}