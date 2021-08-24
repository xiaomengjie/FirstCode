package com.example.firstcode.chapter11.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.firstcode.R

class WebViewActivity : AppCompatActivity() {

    private val webView: WebView by lazy { findViewById(R.id.webView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        //支持JS
        webView.settings.javaScriptEnabled = true
        //当需要从网页跳转到另一个网页时，仍在WebView中显示
        webView.webViewClient = WebViewClient()
        //加载网页地址
        webView.loadUrl("https://www.baidu.com")
    }
}