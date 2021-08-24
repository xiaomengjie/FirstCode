package com.example.firstcode.other

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.firstcode.R

abstract class BaseAbstractActivity: AppCompatActivity() {

    protected val TAG = javaClass.simpleName

    private val buttonListLayout: ButtonListLayout? by lazy {
        initButtonLayout()
    }
    private val buttonBeanList by lazy {
        initButtonBean()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewLayout())
        Log.i("BaseActivity", javaClass.simpleName)
        ActivityCollector.addActivity(this)

        buttonListLayout?.setData(buttonBeanList){
            onClickListener(it)
        }
    }

    protected abstract fun getViewLayout(): Int

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    protected abstract fun initButtonLayout(): ButtonListLayout?

    protected abstract fun initButtonBean(): List<ButtonBean>

    protected abstract fun onClickListener(view: View)
}