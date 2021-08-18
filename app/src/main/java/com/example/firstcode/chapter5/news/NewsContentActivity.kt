package com.example.firstcode.chapter5.news

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstcode.R

class NewsContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title: String? = intent.getStringExtra("title")
        val content: String? = intent.getStringExtra("content")
        if (!title.isNullOrEmpty() && !content.isNullOrEmpty()){
            supportFragmentManager.findFragmentById(R.id.newsContentFragment)?.let {
                if (it is NewsContentFragment){
                    it.refreshContent(title, content)
                }
            }
        }
    }

    companion object{
        fun actionStart(context: Context, title: String, content: String){
            context.startActivity(Intent(context, NewsContentActivity::class.java).apply {
                putExtra("title", title)
                putExtra("content", content)
            })
        }
    }
}