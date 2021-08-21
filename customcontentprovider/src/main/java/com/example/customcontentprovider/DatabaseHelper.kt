package com.example.customcontentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    private val createBook = "create table Book (" +
            "id integer primary key autoincrement," +
            "book_name text," +
            "book_author text," +
            "book_price real," +
            "book_pages integer)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }
}