package com.example.firstcode.chapter7.file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.firstcode.R
import com.example.firstcode.other.showToast
import java.io.*
import java.lang.StringBuilder

class FileStorageActivity : AppCompatActivity() {

    private val contentEdit: EditText by lazy { findViewById(R.id.contentEdit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_storage)

        findViewById<Button>(R.id.save).setOnClickListener {
            val content = contentEdit.text.toString()
            if (content.isNotEmpty()){
                saveToFile(content)
            }
        }

        loadFile().let {
            if (it.isNotEmpty())contentEdit.setText(it)
        }
    }

    private fun loadFile(): String {
        val builder = StringBuilder()
        try {
            val fis = openFileInput("content")
            val read = BufferedReader(InputStreamReader(fis))
            read.use { reader ->
                reader.forEachLine {
                    builder.append(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return builder.toString()
    }

    private fun saveToFile(content: String) {
        try {
            val fos = openFileOutput("content", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(fos))
            //use kotlin内置的扩展函数，lambda执行完后自动关闭数据流
            writer.use {
                it.write(content)
            }
            showToast(this, "save success")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}