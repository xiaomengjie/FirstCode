package com.example.firstcode.chapter13.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO: 2021/8/31 database一定要定义成abstract
@Database(version = 1, entities = [Person::class])
abstract class PersonDatabase: RoomDatabase() {

    // TODO: 2021/8/31 一定要定义获取dao的abstract方法
    //  具体实现由room底层实现
    abstract fun personDao(): PersonDao

    companion object{
        private var instance: PersonDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): PersonDatabase{
            instance?.let {
                return it
            }
            // TODO: 2021/8/31 context一定要使用applicationContext 避免内存泄露
            return Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_database").build().apply {
                    instance = this
                }
        }
    }
}