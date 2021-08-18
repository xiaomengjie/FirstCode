package com.example.firstcode.chapter5.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R

class NewsTitleAdapter(private val list: List<News>, val onClick: (news: News) -> Unit): RecyclerView.Adapter<NewsTitleAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val newTitle: TextView = view.findViewById(R.id.newsTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.news_title_item, parent, false))
        holder.itemView.setOnClickListener {
            val news = list[holder.adapterPosition]
            onClick(news)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            holder.newTitle.text = it.title
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}