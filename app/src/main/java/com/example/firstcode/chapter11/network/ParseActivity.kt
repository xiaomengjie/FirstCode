package com.example.firstcode.chapter11.network

import android.util.Log
import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter11.HttpUtil
import com.example.firstcode.other.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

private const val PULL = 1
private const val SAX = 2
private const val DOM = 3
private const val JSON_OBJECT = 4
private const val GSON = 5

private const val XML_URL = "http://10.0.2.2/get_data.xml"
private const val JSON_URL = "http://10.0.2.2/get_data.json"

class ParseActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_xmlparse
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.xml_parse_with_pull to getString(R.string.xml_parse_with_pull),
            R.string.xml_parse_with_sax to getString(R.string.xml_parse_with_sax),
            R.string.xml_parse_with_dom to getString(R.string.xml_parse_with_dom),
            R.string.json_parse_with_jsonObject to getString(R.string.json_parse_with_jsonObject),
            R.string.json_parse_with_gson to getString(R.string.json_parse_with_gson),
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.xml_parse_with_pull -> {
                thread { sendRequestWithOkHttp(XML_URL, PULL) }
            }
            R.string.xml_parse_with_sax ->{
                thread { sendRequestWithOkHttp(XML_URL, SAX) }
            }
            R.string.xml_parse_with_dom -> {
                thread { sendRequestWithOkHttp(XML_URL, DOM) }
            }
            R.string.json_parse_with_jsonObject -> {
                thread { sendRequestWithOkHttp(JSON_URL, JSON_OBJECT) }
            }
            R.string.json_parse_with_gson -> {
                thread { sendRequestWithOkHttp(JSON_URL, GSON) }
            }
        }
    }

    private fun sendRequestWithOkHttp(url: String, parseMethod: Int){
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(url)
//            .build()
//        val response = client.newCall(request).execute()
//        response.body?.let {
//            parseXML(parseMethod, it.string())
//        }
        HttpUtil.sendRequestWithOkHttp(url, object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { parseXML(parseMethod, it.string()) }
            }
        })
    }

    private fun parseXML(parseMethod: Int, parseData: String){
        when(parseMethod){
            PULL -> { parseXMLWithPull(parseData) }
            SAX -> { parseXMLWithSAX(parseData) }
            DOM -> { parseXMLWithDOM(parseData) }
            JSON_OBJECT -> { parseJsonWithJSONObject(parseData) }
            GSON -> { parseJsonWithGson(parseData) }
        }
    }

    private fun parseXMLWithPull(parseData: String) {
        try {
            val xmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            xmlPullParser.setInput(StringReader(parseData))
            var id = ""
            var name = ""
            var version = ""
            while (xmlPullParser.eventType != XmlPullParser.END_DOCUMENT){
                when(xmlPullParser.eventType){
                    XmlPullParser.START_TAG -> {
                        when(xmlPullParser.name){
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if ("app" == xmlPullParser.name){
                            Log.e(TAG, "parseXMLWithPull: ${NodeBean(id, name, version)}")
                        }
                    }
                }
                xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXMLWithSAX(parseData: String) {
        val saxParser = SAXParserFactory.newInstance().newSAXParser()
        saxParser.parse(InputSource(StringReader(parseData)), XMLHandle())
    }

    private fun parseXMLWithDOM(parseData: String){
        try {
            val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            val document = documentBuilder.parse(InputSource(StringReader(parseData)))
            val nodeList = document.getElementsByTagName("app")
            for (i in 0 until nodeList.length){
                val id = document.getElementsByTagName("id").item(i).firstChild.nodeValue
                val name = document.getElementsByTagName("name").item(i).firstChild.nodeValue
                val version = document.getElementsByTagName("version").item(i).firstChild.nodeValue
                Log.e(TAG, "parseXMLWithDOM: ${NodeBean(id, name, version)}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJsonWithJSONObject(parseData: String) {
        JSONArray(parseData).forEach {
            val id = it.getString("id")
            val name = it.getString("name")
            val version = it.getString("version")
            Log.e(TAG, "parseJsonWithJSONObject: ${NodeBean(id, name, version)}")
        }
    }

    private fun parseJsonWithGson(parseData: String) {
        val gson = Gson()
        val typeToken = object : TypeToken<List<NodeBean>>(){}
        val nodeBeanList = gson.fromJson<List<NodeBean>>(parseData, typeToken.type)
        nodeBeanList.forEach {
            Log.e(TAG, "parseJsonWithGson: $it")
        }
    }
}

data class NodeBean(val id: String, val name: String, val version: String)