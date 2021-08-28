package com.example.firstcode.other

import com.example.firstcode.chapter11.HttpCallbackListener
import com.example.firstcode.chapter11.HttpUtil
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun main() {

    //开启协程
    //GlobalScope.launch 顶层协程，程序运行结束时，协程也会一起结束
    //主线程休眠1秒再结束，此时顶层协程也会结束，无论协程代码是否执行完毕
    GlobalScope.launch {
        println("codes run in coroutine scope")
        // delay 非阻塞式挂起函数
        delay(1500)
        println("codes run in coroutine scope finished")
    }

    Thread.sleep(1000)

    //runBlocking，创建的协程作用域保证：在协程作用域内的代码和子协程没有运行完之前
    //             一直阻塞当前线程
    runBlocking {
        println("codes run in coroutine scope")
        // delay 非阻塞式挂起函数
        delay(1500)
        println("codes run in coroutine scope finished")
    }

    //创建多个协程launch函数
    runBlocking {
        launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }

        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }
    }

    //取消所有协程协程
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {

    }
    job.cancel()

    //获取协程返回值 async
    runBlocking {
        val result = async { 5 + 5 }.await()
        println(result)

        withContext(Dispatchers.Default){
            5 + 5
        }
    }
}

//声明挂起函数：suspend
suspend fun printDot() = coroutineScope {
    launch {
        println(".")
        delay(1000)
    }
}

suspend fun request(url: String): String{
    return suspendCoroutine {
        HttpUtil.sendRequestWithHttpURLConnection(url, object : HttpCallbackListener{
            override fun onFinish(response: String) {
                it.resume(response)
            }

            override fun onError(exception: Exception) {
                it.resumeWithException(exception)
            }

        })
    }
}


