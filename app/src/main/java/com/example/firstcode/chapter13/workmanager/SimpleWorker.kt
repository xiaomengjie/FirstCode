package com.example.firstcode.chapter13.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    // TODO: 2021/9/1 doWork不会运行在主线程
    override fun doWork(): Result {
        Log.e("SimpleWorker", "do work in SimpleWorker", )
        return Result.success()
    }
}