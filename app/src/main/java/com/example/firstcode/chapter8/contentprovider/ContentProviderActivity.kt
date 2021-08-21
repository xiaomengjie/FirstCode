package com.example.firstcode.chapter8.contentprovider

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.other.showToast

private const val BOOK_NAME = "book_name"
private const val BOOK_AUTHOR = "book_author"
private const val BOOK_PRICE = "book_price"
private const val BOOK_PAGES = "book_pages"

private const val CATEGORY_NAME = "category_name"
private const val CATEGORY_CODE = "category_code"

class ContentProviderActivity : BaseActivity() {

    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        //读取联系人属于危险权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        }

        findViewById<Button>(R.id.query_contact).setOnClickListener {
            readContacts()
        }

        findViewById<Button>(R.id.insert_data).setOnClickListener {
            val insertUri = contentResolver.insert(Uri.parse("content://com.example.customcontentprovider.provider/book"), contentValuesOf(
                BOOK_NAME to "你是我的荣耀", BOOK_AUTHOR to "顾漫", BOOK_PRICE to 16.9, BOOK_PAGES to 98
            ))
            contentResolver.insert(Uri.parse("content://com.example.customcontentprovider.provider/book"), contentValuesOf(
                BOOK_NAME to "微微一笑很倾城", BOOK_AUTHOR to "顾漫", BOOK_PRICE to 19.9, BOOK_PAGES to 108
            ))
            bookId = insertUri?.pathSegments?.get(1)
            showToast(this, "insert success")
        }

        findViewById<Button>(R.id.delete_data).setOnClickListener {
            contentResolver.delete(Uri.parse("content://com.example.customcontentprovider.provider/book"),
            null, null)
            showToast(this, "delete success")
        }

        findViewById<Button>(R.id.update_data).setOnClickListener {
            bookId?.let {
                contentResolver.update(Uri.parse("content://com.example.customcontentprovider.provider/book/$it"),
                    contentValuesOf(BOOK_NAME to "何以笙箫默"), null, null)
                showToast(this, "update success")
            }
        }

        findViewById<Button>(R.id.query_data).setOnClickListener {
            val cursor = contentResolver.query(Uri.parse("content://com.example.customcontentprovider.provider/book")
                ,null, null, null, null)
            cursor?.let {
                if (it.moveToFirst()){
                    do {
                        val name = it.getString(it.getColumnIndex(BOOK_NAME))
                        val author = it.getString(it.getColumnIndex(BOOK_AUTHOR))
                        val price = it.getFloat(it.getColumnIndex(BOOK_PRICE))
                        val pages = it.getInt(it.getColumnIndex(BOOK_PAGES))
                        Log.e(TAG, "name = $name, author = $author, price = $price, pages = $pages")
                    }while (it.moveToNext())
                }
                it.close()
            }
        }
    }

    private fun readContacts() {
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)?.use {
                while (it.moveToNext()){
                    val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    Log.e(TAG, "name is $name, phone is $phone")
                }
        }
    }
}