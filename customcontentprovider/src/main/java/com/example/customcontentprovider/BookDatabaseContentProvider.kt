package com.example.customcontentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class BookDatabaseContentProvider : ContentProvider() {
    private var databaseHelper: DatabaseHelper? = null

    private val book = 1
    private val bookItem = 2
    private val category = 3
    private val categoryItem = 4
    private val authority = "com.example.customcontentprovider.provider"
    private val uriMatcher by lazy {
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(authority, "book", book)
        uriMatcher.addURI(authority, "book/#", bookItem)
        uriMatcher.addURI(authority, "category", category)
        uriMatcher.addURI(authority, "category/#", categoryItem)
        uriMatcher
    }

    override fun onCreate(): Boolean {
        context?.let {
            databaseHelper = DatabaseHelper(it, "BookStore.db", 1)
            return true
        }
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        databaseHelper?.let {
            val database = it.writableDatabase
            return when(uriMatcher.match(uri)){
                book -> {
                    database.query("Book", projection, selection, selectionArgs,
                        null, null, sortOrder)
                }
                bookItem -> {
                    val bookId = uri.pathSegments[1]
                    database.query("Book", projection, "id = ?", arrayOf(bookId),
                        null, null, sortOrder)
                }
                category -> {
                    database.query("Category", projection, selection, selectionArgs,
                        null, null, sortOrder)
                }
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    database.query("Category", projection, "id = ?", arrayOf(categoryId),
                        null, null, sortOrder)
                }
                else -> null
            }
        }
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        databaseHelper?.let {
            val database = it.writableDatabase
            return when(uriMatcher.match(uri)){
                book, bookItem -> {
                    val bookId = database.insert("Book", null, values)
                    Uri.parse("content://$authority/book/$bookId")
                }
                category, categoryItem -> {
                    val categoryId = database.insert("Category", null, values)
                    Uri.parse("content://$authority/category/$categoryId")
                }
                else -> null
            }
        }
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        databaseHelper?.let {
            val database = it.writableDatabase
            return when(uriMatcher.match(uri)){
                book -> {
                    database.update("Book", values, selection, selectionArgs)
                }
                bookItem -> {
                    database.update("Book", values, "id = ?", arrayOf(uri.pathSegments[1]))
                }
                category -> {
                    database.update("Category", values, selection, selectionArgs)
                }
                categoryItem -> {
                    database.update("Category", values, "id = ?", arrayOf(uri.pathSegments[1]))
                }
                else -> 0
            }
        }
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        databaseHelper?.let {
            val database = it.writableDatabase
            return when(uriMatcher.match(uri)){
                book -> {
                    database.delete("Book", selection, selectionArgs)
                }
                bookItem -> {
                    database.delete("Book", "id = ?", arrayOf(uri.pathSegments[1]))
                }
                category -> {
                    database.delete("Category", selection, selectionArgs)
                }
                categoryItem -> {
                    database.delete("Category", "id = ?", arrayOf(uri.pathSegments[1]))
                }
                else -> 0
            }
        }
        return 0
    }

    override fun getType(uri: Uri): String? {
        return when(uriMatcher.match(uri)){
            book -> {
                "vnd.android.cursor.dir/vnd.com.example.customcontentprovider.provider.book"
            }
            bookItem -> {
                "vnd.android.cursor.item/vnd.com.example.customcontentprovider.provider.book"
            }
            category -> {
                "vnd.android.cursor.dir/vnd.com.example.customcontentprovider.provider.category"
            }
            categoryItem -> {
                "vnd.android.cursor.item/vnd.com.example.customcontentprovider.provider.category"
            }
            else -> null
        }
    }
}