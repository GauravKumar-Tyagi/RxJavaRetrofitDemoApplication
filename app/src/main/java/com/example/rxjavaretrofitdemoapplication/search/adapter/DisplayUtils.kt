package com.example.rxjavaretrofitdemoapplication.search.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowMetrics

object DisplayUtils {

    fun dpToPx(dp: Float): Float = dp * Resources.getSystem().displayMetrics.density

    fun pxToDp(px: Float): Float = px / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / 160f)

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels




    //




    fun calculateCellHeight(context: Context?, itemsOnScreen: Int): Int {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {

            val display : Display? = context?.display
            /*val size = Point()
            display?.getSize(size)
            val screenWidth = size.x*/

            var displayMetrics : DisplayMetrics = Resources.getSystem().displayMetrics
            var px : Float = displayMetrics?.heightPixels?.toFloat()
            Log.e("~~~~~~!!!@!~~~!@!~"," screenHeight  0 :: "+px)
            var screenHeight : Int?= pxToDp(px ?: 0.0f)?.toInt()

            if(screenHeight != null){
                Log.e("~~~~~~!!!@!~~~!@!~"," screenHeight  1 :: "+screenHeight)
                return screenHeight / itemsOnScreen
            }
            else{
                val screenHeight = DisplayUtils.getScreenHeight()
                Log.e("~~~~~~!!!@!~~~!@!~"," screenHeight  2 :: "+screenHeight)
                return (screenHeight/itemsOnScreen)-20
            }
        }else{
            val screenHeight = DisplayUtils.getScreenHeight()
            Log.e("~~~~~~!!!@!~~~!@!~"," screenHeight  3 :: "+screenHeight)
            return (screenHeight/itemsOnScreen)-20
        }
    }

    ///


}