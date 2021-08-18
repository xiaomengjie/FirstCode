package com.example.firstcode.chapter5.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R
import com.example.firstcode.other.actionStart
import java.lang.StringBuilder

class NewsActivity : AppCompatActivity() {

    private val titleList: RecyclerView by lazy { findViewById(R.id.titleList) }
    private lateinit var adapter: NewsTitleAdapter
    private val contentFragment by lazy { supportFragmentManager.findFragmentById(R.id.newsContentFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        adapter = NewsTitleAdapter(getNews()){ news ->
            if (contentFragment == null){
                NewsContentActivity.actionStart(this, news.title, news.content)
            }else{
                contentFragment?.let {
                    if (it is NewsContentFragment){
                        it.refreshContent(news.title, news.content)
                    }
                }
            }
        }
        titleList.layoutManager = LinearLayoutManager(this)
        titleList.adapter = adapter
    }

    private fun getNews(): List<News>{
        val newsList = mutableListOf<News>()
        for (i in 1 ..50){
            val news = News("This is news title $i", getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(content: String): String{
        val n = (1 .. 20).random()
        return StringBuilder().let { builder ->
            repeat(n){builder.append(content)}
            builder.toString()
        }
    }
}