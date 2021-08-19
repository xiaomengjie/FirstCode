package com.example.firstcode.chapter7.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstcode.other.showToast

class SQLiteDatabaseHelper(
    val context: Context,
    databaseName: String,
    version: Int): SQLiteOpenHelper(context, databaseName, null, version) {

    /**
     * SQLite中的数据类型
     *  integer：整型
     *  text：文本类型
     *  real：浮点型
     * 此语句的含义：创建一个Book表，表中有5个字段
     * id 主键且自动增长
     * author(作者) 和 name(书名)为文本类型
     * price(价格)为浮点型 pages(总的页数)为整型
     */
    private val createBook = "create table Book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
        showToast(context, "create success")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists Book")
        db.execSQL("drop table if exists Category")
        onCreate(db)
    }
}