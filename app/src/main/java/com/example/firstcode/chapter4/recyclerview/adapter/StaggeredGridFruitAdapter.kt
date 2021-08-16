package com.example.firstcode.chapter4.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R
import com.example.firstcode.chapter4.listview.FruitBean
import com.example.firstcode.other.showToast

class StaggeredGridFruitAdapter(private val fruitList: List<FruitBean>): RecyclerView.Adapter<StaggeredGridFruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_staggered_grid, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruitBean = fruitList[position]
            showToast(parent.context, "you clicked view ${fruitBean.name}")
        }
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruitBean = fruitList[position]
            showToast(parent.context, "you clicked view ${fruitBean.name}")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = fruitList[position]
        holder.fruitImage.setImageResource(item.imageId)
        holder.fruitName.text = item.name
    }

    override fun getItemCount(): Int = fruitList.size
}