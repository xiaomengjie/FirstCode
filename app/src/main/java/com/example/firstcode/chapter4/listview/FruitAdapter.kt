package com.example.firstcode.chapter4.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.firstcode.R

class FruitAdapter(context: Context, private val resId: Int, data: List<FruitBean>):
    ArrayAdapter<FruitBean>(context, resId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(resId, parent, false)
            val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
            val fruitName = view.findViewById<TextView>(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        getItem(position)?.let {
            viewHolder.fruitImage.setImageResource(it.imageId)
            viewHolder.fruitName.text = it.name
        }
        return view
    }

    inner class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)
}