package com.example.firstcode.chapter11

import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter11.network.NetworkActivity
import com.example.firstcode.chapter11.network.ParseActivity
import com.example.firstcode.chapter11.webview.WebViewActivity
import com.example.firstcode.other.*

class InternetPracticeActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_internet_practice
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.web_view to getString(R.string.web_view),
            R.string.network to getString(R.string.network),
            R.string.parse to getString(R.string.parse)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.web_view -> {
                startActivity<WebViewActivity>(this)
            }
            R.string.network -> {
                startActivity<NetworkActivity>(this)
            }
            R.string.parse -> {
                startActivity<ParseActivity>(this)
            }
        }
    }
}