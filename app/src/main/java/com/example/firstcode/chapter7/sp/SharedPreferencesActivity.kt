package com.example.firstcode.chapter7.sp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.other.showToast

const val CONTEXT = 1
const val NAME = "name"
const val AGE = "age"
const val MARRIED = "married"

class SharedPreferencesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        findViewById<Button>(R.id.put_to_sp).setOnClickListener {
            saveToSp()
        }

        findViewById<Button>(R.id.get_from_sp).setOnClickListener {
            getFromSp()
        }
    }

    private fun getFromSp() {
        //1、获取SP对象
        val sharedPreferences = getSharedPreferences(CONTEXT)
        //2、调用对应方法通过设置的key，读取数据
        val name = sharedPreferences.getString(NAME, null)
        val age = sharedPreferences.getInt(AGE, 18)
        val married = sharedPreferences.getBoolean(MARRIED, false)
        Log.e(TAG, "name is $name, age = $age, is married = $married")
    }

    private fun saveToSp() {
        //1、获取SP对象
        val sharedPreferences = getSharedPreferences(CONTEXT)
        //2、获取edit对象
        val edit = sharedPreferences.edit()
        //3、调用对应方法设置数据
        edit.putString(NAME, "xiao")
        edit.putInt(AGE, 28)
        edit.putBoolean(MARRIED, false)
        //4、数据提交，完成存储
        edit.apply()
        showToast(this, "save success")
    }

    private fun getSharedPreferences(type: Int): SharedPreferences{
        return if (type == CONTEXT){
            /**
             * context下的getSharedPreferences(文件名，操作模式)方法
             * 模式只有一种：Context.MODE_PRIVATE，表示只有当前程序才能读写
             */
            getSharedPreferences("data", Context.MODE_PRIVATE)
        }else{
            /**
             * activity下的getPreferences(操作模式)
             * 系统会将activity类名当作文件名
             */
            getPreferences(Context.MODE_PRIVATE)
        }
    }

}