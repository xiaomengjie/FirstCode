package com.example.firstcode.chapter14

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

class Person: Serializable{
    var name: String = ""
    var age: Int = 0
}

class Student : Parcelable{

    var name: String = ""
    var age: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    // TODO: 2021/9/1 0001 读写顺序要相同，先写string，就需要先读string
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            val student = Student()
            student.name = parcel.readString()?:""
            student.age = parcel.readInt()
            return Student()
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}

// TODO: 2021/9/1 0001 Kotlin 提供了简便parcelable方法
//  需要添加插件 kotlin-parcelize
@Parcelize
class Teacher(var name: String, var age: Int): Parcelable

fun intentWithParams(context: Activity){
    // TODO: 2021/9/1 0001 intent传递数据bean时，数据bean需要实现序列化
    //  Serializable：先序列化为可传输状态，由另一个activity反序列化为一个新的对象
    //                  两个对象存储数据相同，但不是同一个对象
    //  Parcelable：将数据bean先分解为每个部分intent都支持传递的类型，再传递，
    //                  读写顺序要相同，先写string，就需要先读string
    Intent().apply {
        putExtra("bean", Person())
    }
    context.intent.getSerializableExtra("bean")

    Intent().apply {
        putExtra("student", String())
    }
    context.intent.getParcelableExtra<Student>("student")
}

//判断当前系统是否为深色主题
fun isDarkTheme(context: Context): Boolean{
    val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return flag == Configuration.UI_MODE_NIGHT_YES
}