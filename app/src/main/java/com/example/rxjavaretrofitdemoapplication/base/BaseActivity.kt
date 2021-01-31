package com.example.rxjavaretrofitdemoapplication.base

import android.app.ProgressDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open abstract class BaseActivity : AppCompatActivity() {
    private var mProgressDialog: ProgressDialog? = null

    fun showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            // display a progress dialog

            // display a progress dialog
            mProgressDialog?.setCancelable(false) // set cancelable to false

            mProgressDialog?.setMessage("Please Wait") // set message

        }
        mProgressDialog?.show() // show progress dialog
    }

    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }

    fun showLoading(show: Boolean?) {
        if (show!!) showProgress() else hideProgress()
    }

    fun showSnackBar(message: String, content: View) {
        this.let {
            Snackbar.make(content, message, Snackbar.LENGTH_LONG).show()
        }
    }

}