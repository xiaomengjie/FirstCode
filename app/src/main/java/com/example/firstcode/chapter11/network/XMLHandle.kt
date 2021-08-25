package com.example.firstcode.chapter11.network

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class XMLHandle: DefaultHandler() {

    private val TAG = javaClass.simpleName

    private var nodeName = ""

    private lateinit var id: StringBuilder
    private lateinit var name: StringBuilder
    private lateinit var version: StringBuilder

    //开始解析XML时调用
    override fun startDocument() {
        super.startDocument()
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    //开始解析某个节点时调用
    override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
        super.startElement(uri, localName, qName, attributes)
        nodeName = localName
    }

    //某个节点解析完成时调用
    override fun endElement(uri: String, localName: String, qName: String) {
        super.endElement(uri, localName, qName)
        if ("app" == localName){
            Log.e(TAG, "parseXMLWithSAX: " +
                    "${NodeBean(id.toString().trim(), name.toString().trim(), version.toString().trim())}")

            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    //XML解析完成时调用
    override fun endDocument() {
        super.endDocument()
    }

    //获取节点内容时调用
    override fun characters(ch: CharArray, start: Int, length: Int) {
        super.characters(ch, start, length)
        when(nodeName){
            "id" -> id.append(ch, start, length)
            "name" -> name.append(ch, start, length)
            "version" -> version.append(ch, start, length)
        }
    }
}