package com.example.firstcode.chapter7.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstcode.other.showToast

class SQLiteDatabaseUpgrade(
    val context: Context,
    databaseName: String,
    version: Int): SQLiteOpenHelper(context, databaseName, null, version) {

    private val createBook = "create table Book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id integer)"

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
        if (oldVersion <= 1){
            db.execSQL(createCategory)
        }
        if (oldVersion <= 2){
            db.execSQL("alter table Book add column category_id integer")
        }
    }
}