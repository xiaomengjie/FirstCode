package com.example.firstcode.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.chapter7.file.FileStorageActivity
import com.example.firstcode.chapter7.sp.SharedPreferencesActivity
import com.example.firstcode.other.actionStart

class DataStoragePracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_storage_practice)

        findViewById<Button>(R.id.file_storage).setOnClickListener {
            actionStart(this, FileStorageActivity::class.java)
        }
        findViewById<Button>(R.id.shared_preferences).setOnClickListener {
            actionStart(this, SharedPreferencesActivity::class.java)
        }
    }
}