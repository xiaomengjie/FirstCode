package com.example.firstcode.chapter11.network

import android.util.Log
import android.view.View
import com.example.firstcode.R
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.to
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import kotlin.concurrent.thread

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
                thread { sendRequestWithPull() }
            }
        }
    }

    private fun sendRequestWithPull() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://10.0.2.2/get_data.xml")
            .build()
        val response = client.newCall(request).execute()
        response.body?.let { parseXMLWithPull(it.string()) }
    }

    private fun parseXMLWithPull(string: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val pullParser = factory.newPullParser()
            pullParser.setInput(StringReader(string))
            var eventType = pullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT){
                val nodeName = pullParser.name
                when(eventType){
                    XmlPullParser.START_TAG -> {
                        when(nodeName){
                            "id" -> id = pullParser.nextText()
                            "name" -> name = pullParser.nextText()
                            "version" -> version = pullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName){
                            Log.e(TAG, "id = $id")
                            Log.e(TAG, "name = $name")
                            Log.e(TAG, "version = $version")
                        }
                    }
                }
                eventType = pullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}