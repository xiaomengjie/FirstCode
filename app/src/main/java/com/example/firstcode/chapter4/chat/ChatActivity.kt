package com.example.firstcode.chapter4.chat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity

class ChatActivity : BaseActivity() {

    private val msgList: MutableList<Msg> = mutableListOf()

    private lateinit var adapter: MsgAdapter

    private val inputText: EditText by lazy { findViewById(R.id.inputText) }
    private val chatRecyclerView: RecyclerView by lazy { findViewById(R.id.chat_recyclerView) }

    private var type = Msg.TYPE_RECEIVED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MsgAdapter(msgList)
        chatRecyclerView.adapter = adapter

        findViewById<Button>(R.id.send).setOnClickListener {
            val content = inputText.text.toString()
            if (content.isNotEmpty()){
                val msg = Msg(content, type)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size - 1)
                chatRecyclerView.scrollToPosition(msgList.size - 1)
                inputText.setText("")
                type = if (type == Msg.TYPE_RECEIVED) Msg.TYPE_SEND else Msg.TYPE_RECEIVED
            }
        }
    }
}