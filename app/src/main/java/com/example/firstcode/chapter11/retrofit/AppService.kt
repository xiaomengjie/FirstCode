package com.example.firstcode.chapter11.retrofit

import com.example.firstcode.chapter11.network.NodeBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AppService {

    @GET("get_data.json")
    fun getAppData(): Call<List<NodeBean>>


    // TODO: 2021/8/28 静态地址 http://example.com/get_data.json
    @GET("get_data.json")
    fun getData(): Call<NodeBean>

    // TODO: 2021/8/28 动态地址 http://example.com/<page>/get_data.json
    // page代表页数
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page: Int): Call<NodeBean>

    // TODO: 2021/8/28 参数传递 http://example.com/get_data.josn?u=<user>&t=<token>
    @GET("get_data.json")
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<NodeBean>

    // TODO: 2021/8/28 请求类型
    //  GET：获取服务器数据
    //  POST：向服务器提交数据
    //  PUT和PATCH：请求修改服务器数据
    //  DELETE：请求删除服务器数据

    // TODO: 2021/8/28 删除数据 http://example.com/data/<id> 删除id为某值的数据
    @DELETE("data/{id}")
    fun deleteData(@Path("id") id: String): Call<ResponseBody>

    // TODO: 2021/8/28 提交数据 http://example.com/data/create
    //      {"id": 1, "content": "The description for this data"}
    @POST("data/create")
    fun postData(@Body data: Data): Call<ResponseBody>

    // TODO: 2021/8/28 添加请求头
    @GET("get_data.json")
    @Headers("User-Agent: okhttp", "Cache-Control: max-age=0")
    fun getDataWithHeader(): Call<Data>

    @GET("get_data.json")
    fun getDataWithHeader(@Header("User-Agent") user: String,
                            @Header("Cache-Control") cacheControl: String): Call<Data>
}

data class Data(val id: Int, val content: String)