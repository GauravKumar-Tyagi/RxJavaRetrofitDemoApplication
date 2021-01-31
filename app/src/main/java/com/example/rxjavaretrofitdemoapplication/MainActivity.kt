package com.example.rxjavaretrofitdemoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjavaretrofitdemoapplication.oneAPIcall.OneActivity
import com.example.rxjavaretrofitdemoapplication.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click()
    }

    fun click(){

        idBtn1?.setOnClickListener {
            var intent = Intent(MainActivity@this,OneActivity::class.java)
            startActivity(intent)
        }

        idBtn2?.setOnClickListener {
            var intent = Intent(MainActivity@this, SearchActivity::class.java)
            startActivity(intent)
        }

    }
}