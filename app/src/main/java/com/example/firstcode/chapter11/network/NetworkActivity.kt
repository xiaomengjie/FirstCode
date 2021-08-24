package com.example.firstcode.chapter11.network

import android.view.View
import android.widget.TextView
import com.example.firstcode.R
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.to
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_network
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.httpURLConnection to getString(R.string.httpURLConnection),
            R.string.okhttp to getString(R.string.okhttp),
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.httpURLConnection -> {
                thread { sendRequestWithHttpURLConnection() }
            }
            R.string.okhttp -> {
                thread { sendRequestWithOkHttp() }
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.baidu.com").build()
        val response = client.newCall(request).execute()
        response.body?.let {
            showResponse(it.string())
        }

        //发送POST请求，需要post指定requestBody参数，携带要传递的参数
        if (false){
            val requestBody = FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456").build()
            val request = Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody).build()
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        var connection: HttpURLConnection? = null
        try {
            connection = URL("https://www.baidu.com").openConnection() as HttpURLConnection
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            connection.requestMethod = "GET"
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.use {
                it.forEachLine { line ->
                    stringBuilder.append(line)
                }
            }
            showResponse(stringBuilder.toString())

            //如果需要写数据
            if (false){
                connection.requestMethod = "POST"
                val dataOutputStream = DataOutputStream(connection.outputStream)
                dataOutputStream.writeBytes("username=admin&password=123456")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
    }

    private val responseText: TextView by lazy { findViewById(R.id.responseText) }
    private fun showResponse(response: String) {
        runOnUiThread { responseText.text = response }
    }
}