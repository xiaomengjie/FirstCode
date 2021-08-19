package com.example.firstcode.chapter7.database

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.other.showToast

class DatabaseActivity : BaseActivity() {

    private val databaseHelper: SQLiteDatabaseHelper by lazy {
        SQLiteDatabaseHelper(this, "BookStore.db", 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        findViewById<Button>(R.id.createDatabase).setOnClickListener {
            databaseHelper.writableDatabase
        }

        findViewById<Button>(R.id.add_data).setOnClickListener {
            val database = databaseHelper.writableDatabase
            database.insert("Book", null, ContentValues().apply {
                put("author", "顾漫")
                put("price", 99.9)
                put("pages", 359)
                put("name", "你是我的荣耀")
            })
            database.insert("Book", null, ContentValues().apply {
                put("author", "顾漫")
                put("price", 99.9)
                put("pages", 159)
                put("name", "微微一笑很倾城")
            })
            showToast(this, "insert success")
        }

        findViewById<Button>(R.id.delete_data).setOnClickListener {
            val database = databaseHelper.writableDatabase
            database.delete("Book", "name = ?", arrayOf("你是我的荣耀"))
            showToast(this, "delete success")
        }

        findViewById<Button>(R.id.update_data).setOnClickListener {
            val database = databaseHelper.writableDatabase
            database.update("Book", ContentValues().apply {
                put("pages", 596)
            }, "name = ?", arrayOf("微微一笑很倾城"))
            showToast(this, "update success")
        }

        findViewById<Button>(R.id.query_data).setOnClickListener {
            val database = databaseHelper.writableDatabase
            val cursor = database.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()){
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val price = cursor.getFloat(cursor.getColumnIndex("price"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    Log.e(TAG, "name = $name, author = $author, price = $price, pages = $pages")
                }while (cursor.moveToNext())
            }
        }
    }
}