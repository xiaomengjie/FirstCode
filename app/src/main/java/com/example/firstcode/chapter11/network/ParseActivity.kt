package com.example.firstcode.chapter11.network

import android.view.View
import com.example.firstcode.R
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.to

class ParseActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_xmlparse
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.xml_parse_with_pull to getString(R.string.xml_parse_with_pull)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.xml_parse_with_pull -> {

            }
        }
    }
}