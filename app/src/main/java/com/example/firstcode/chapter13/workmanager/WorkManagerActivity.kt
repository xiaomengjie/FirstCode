package com.example.firstcode.chapter13.workmanager

import android.util.Log
import android.view.View
import androidx.work.*
import com.example.firstcode.R
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.to
import java.util.concurrent.TimeUnit

class WorkManagerActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_work_manager
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.doWork to getString(R.string.doWork),
            R.string.calcelWork to getString(R.string.calcelWork)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.doWork -> {
                // TODO: 2021/9/1 OneTimeWorkRequest构建单次运行的后台任务请求
                // setInitialDelay：指定后台任务延时执行，单位有毫秒、秒、分钟、小时、天
                // addTag：添加标签，可以用来取消同类标签的所有后台任务
                // setBackoffCriteria：设置如何重新执行任务，当定义的任务中返回值为Result.retry()时有效
                /**
                 * setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.MINUTES)
                 * 第一个参数：任务延迟执行的方式
                 *      LINEAR：线性延时 10 20 30 40
                 *      EXPONENTIAL：指数延时 10 100 1000
                 * 第二，三个参数：指定延时时间，不少于10分钟
                 */
                val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                    .setInitialDelay(30, TimeUnit.SECONDS)
                    .addTag("simple")
                    .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.MINUTES)
                    .build()
                // TODO: 2021/9/1 可以用来观察任务的返回结果
                //  Result.success() 对应状态 WorkInfo.State.SUCCEEDED
                //  Result.failure() 对应状态 WorkInfo.State.FAILED
                //  当返回为Result.retry()时，会根据setBackoffCriteria中设置的参数重新执行
                WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                    .observe(this){
                        if (it.state == WorkInfo.State.SUCCEEDED){
                            Log.e(TAG, "do work succeeded")
                        } else if (it.state == WorkInfo.State.FAILED){
                            Log.e(TAG, "do work failed")
                        }
                    }
                WorkManager.getInstance(this).enqueue(request)
                // TODO: 2021/9/1 PeriodicWorkRequest构建周期性后台任务请求，运行周期间隔不能短于15分钟
//                PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
            }
            R.string.calcelWork -> {
                // TODO: 2021/9/1 取消后台任务请求
//                WorkManager.getInstance(this).cancelAllWork()
                WorkManager.getInstance(this).cancelAllWorkByTag("simple")
//                WorkManager.getInstance(this).cancelWorkById(request.id)
            }
        }
    }

    override fun initView() {
        // TODO: 2021/9/1 beginWith已执行哪个任务开始 then接着执行哪个任务
        //  必须前一个任务执行成功后下一个任务才会运行
        WorkManager.getInstance(this)
            .beginWith(OneTimeWorkRequest.from(SimpleWorker::class.java))
            .then(OneTimeWorkRequest.from(SimpleWorker::class.java))
            .then(OneTimeWorkRequest.from(SimpleWorker::class.java))
            .enqueue()
    }
}