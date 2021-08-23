package com.example.firstcode.chapter10.thread

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.firstcode.R
import com.example.firstcode.other.actionStart
import kotlin.concurrent.thread

private const val UPDATE_TEXT = 1

class ThreadActivity : AppCompatActivity() {

    private val handleMessageText: TextView by lazy {
        findViewById(R.id.showHandleMessage)
    }

    private val handle: Handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                UPDATE_TEXT -> {
                    handleMessageText.text = "Nice to meet you"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        findViewById<Button>(R.id.createThread).setOnClickListener {
            /**
             * 开启线程，2种方法
             *  1、继承Thread
             *  2、继承Runnable
             */
            CustomThread().start()
            Thread(CustomRunnable()).start()
            //lambda表达式
            Thread{}.start()
            //kotlin自带高阶函数
            thread {}
        }

        findViewById<Button>(R.id.handleMessage).setOnClickListener {
            thread {
                handle.sendMessage(Message.obtain().apply {
                    what = UPDATE_TEXT
                })
            }
        }

        findViewById<Button>(R.id.asyncTask).setOnClickListener {
            CustomAsyncTask().execute("")
        }
    }

    inner class CustomAsyncTask: AsyncTask<String, Int, Boolean>(){

        override fun onPreExecute() {
            //后台任务开始前，UI线程，弹出进度框
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): Boolean {
            //执行后台任务，子线程，调用publishProgress通知需要更新进度
            publishProgress(10)
            return true
        }

        override fun onProgressUpdate(vararg values: Int?) {
            //任务进度更新，调用publishProgress时调用，UI线程，具体的UI进度展示
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Boolean?) {
            //任务完成调用，UI线程，隐藏进度框
            super.onPostExecute(result)
        }
    }

    inner class CustomThread: Thread(){
        override fun run() {
        }
    }

    inner class CustomRunnable: Runnable{
        override fun run() {
        }
    }
}