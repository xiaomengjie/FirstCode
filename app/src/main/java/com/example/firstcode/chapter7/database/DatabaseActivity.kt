package com.example.firstcode.chapter7.database

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.other.showToast
import java.lang.NullPointerException

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

        findViewById<Button>(R.id.insert_data).setOnClickListener {
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
            cursor.close()
        }

        findViewById<Button>(R.id.insert_data_use_sql).setOnClickListener {
            databaseHelper.writableDatabase.apply {
                execSQL("insert into Book (name, author, price, pages) values (?, ?, ?, ?)",
                    arrayOf("何以笙箫默", "顾漫", 12.5, 120))
                execSQL("insert into Book (name, author, price, pages) values (?, ?, ?, ?)",
                    arrayOf("杉杉来了", "顾漫", 17.0, 196))
                execSQL("insert into Book (name, author, price, pages) values (?, ?, ?, ?)",
                    arrayOf("骄阳似我", "顾漫", 14.7, 156))
            }
            showToast(this, "insert success")
        }

        findViewById<Button>(R.id.delete_data_use_sql).setOnClickListener {
            databaseHelper.writableDatabase.execSQL(
                "delete from Book where pages < ?", arrayOf(150)
            )
            showToast(this, "delete success")
        }

        findViewById<Button>(R.id.update_data_use_sql).setOnClickListener {
            databaseHelper.writableDatabase.execSQL(
                "update Book set price = ? where name = ?", arrayOf(17.5, "杉杉来了")
            )
            showToast(this, "update success")
        }

        findViewById<Button>(R.id.query_data_use_sql).setOnClickListener {
            val database = databaseHelper.writableDatabase
            val cursor =  database.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()){
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val price = cursor.getFloat(cursor.getColumnIndex("price"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    Log.e(TAG, "name = $name, author = $author, price = $price, pages = $pages")
                }while (cursor.moveToNext())
            }
            cursor.close()
        }

        findViewById<Button>(R.id.use_transaction).setOnClickListener {
            val database = databaseHelper.writableDatabase
            database.beginTransaction()//开启事务
            try {
                database.delete("Book", null, null)
                database.insert("Book", null, ContentValues().apply {
                    put("name", "微微一笑很倾城")
                    put("author", "顾漫")
                    put("price", 12.6)
                    put("pages", 129)
                })
                database.setTransactionSuccessful()//事务执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                database.endTransaction()//结束事务
            }
        }
    }
}