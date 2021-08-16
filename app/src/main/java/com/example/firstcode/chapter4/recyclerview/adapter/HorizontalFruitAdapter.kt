package com.example.firstcode.chapter4.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R
import com.example.firstcode.chapter4.listview.FruitBean

class HorizontalFruitAdapter(private val fruitList: List<FruitBean>): RecyclerView.Adapter<HorizontalFruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = fruitList[position]
        holder.fruitImage.setImageResource(item.imageId)
        holder.fruitName.text = item.name
    }

    override fun getItemCount(): Int = fruitList.size
}