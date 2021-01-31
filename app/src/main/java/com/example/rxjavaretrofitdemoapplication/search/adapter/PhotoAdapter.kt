package com.example.rxjavaretrofitdemoapplication.search.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofitdemoapplication.R
import com.example.rxjavaretrofitdemoapplication.search.response.PhotoX
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotoAdapter(val itemList: ArrayList<PhotoX>, private val listener : (data_item: PhotoX) -> Unit) :
        RecyclerView.Adapter<PhotoAdapter.MyTimeViewHolder>() {

    var context: Context? = null

    inner class MyTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mHeight : Int = DisplayUtils.calculateCellHeight(context,3)-7

        fun bind(item: PhotoX, listener: (PhotoX) -> Unit) {
            setMyItemHeight()
            var link : String = originalUrl(item)
            var uri = Uri.parse(link)
            itemView?.my_image_view?.setImageURI(uri)
            itemView?.idTitle?.text = item?.title
            itemView.setOnClickListener {
                listener(item)
            }
        }
        fun setMyItemHeight(){
            itemView.my_image_view.minimumHeight = mHeight
            itemView.my_image_view.maxHeight = mHeight
            //itemView?.layoutParams.width=mHeight
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTimeViewHolder {
        context = parent?.context
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_photo, parent, false)
        return MyTimeViewHolder(v)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onBindViewHolder(holder: MyTimeViewHolder, position: Int) {
        holder.bind(itemList[position],listener)
    }


    private fun originalUrl(item: PhotoX) : String {
        return item.url_o ?: item.url_l ?: item.url_c ?: item.url_z ?: item.url_n ?: item.url_m
        ?: item.url_q ?: item.url_s ?: item.url_t ?: item.url_sq ?: ""
    }
}


