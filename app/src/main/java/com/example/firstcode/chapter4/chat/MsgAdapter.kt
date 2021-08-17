package com.example.firstcode.chapter4.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R

class MsgAdapter(private val msgList: List<Msg>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Msg.TYPE_RECEIVED){
            LeftViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_item_left, parent, false))
        }else{
            RightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_item_right, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> {
                holder.leftMsg.text = msg.content
            }
            is RightViewHolder -> {
                holder.rightMsg.text = msg.content
            }
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun getItemViewType(position: Int): Int {
        return msgList[position].type
    }

    inner class LeftViewHolder(view: View): RecyclerView.ViewHolder(view){
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

    inner class RightViewHolder(view: View): RecyclerView.ViewHolder(view){
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }
}