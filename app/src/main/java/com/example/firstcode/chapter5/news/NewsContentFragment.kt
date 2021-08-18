package com.example.firstcode.chapter5.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.firstcode.R

class NewsContentFragment : Fragment() {

    private val newsTitle: TextView? by lazy { view?.findViewById(R.id.newsTitle) }
    private val newsContent: TextView? by lazy { view?.findViewById(R.id.newsContent) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refreshContent(title: String, content: String) {
        newsTitle?.let { it.text = title }
        newsContent?.let { it.text = content }
    }
}