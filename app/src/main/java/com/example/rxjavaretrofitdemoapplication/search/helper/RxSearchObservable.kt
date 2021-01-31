package com.example.rxjavaretrofitdemoapplication.search.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object RxSearchObservable {

    fun fromView(editTxt: EditText): Observable<String> {

        val subject : PublishSubject<String> = PublishSubject.create<String>()

        editTxt?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                subject.onNext(s?.toString()?.trim())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        return subject
    }


}