package com.example.firstcode.chapter11.retrofit

import android.util.Log
import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter11.network.NodeBean
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.to
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_retrofit
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.get_app_data to getString(R.string.get_app_data)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.get_app_data -> {
                sendRequestWithRetrofit()
            }
        }
    }

    private fun sendRequestWithRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val create = retrofit.create(AppService::class.java)
        create.getAppData().enqueue(object : Callback<List<NodeBean>>{
            override fun onResponse(call: Call<List<NodeBean>>, response: Response<List<NodeBean>>) {
                response.body()?.forEach {
                    Log.e(TAG, "onResponse: $it")
                }
            }

            override fun onFailure(call: Call<List<NodeBean>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}