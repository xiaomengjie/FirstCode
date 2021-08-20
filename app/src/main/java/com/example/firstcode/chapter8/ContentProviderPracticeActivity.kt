package com.example.firstcode.chapter8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.chapter8.contentprovider.ContentProviderActivity
import com.example.firstcode.chapter8.permission.RuntimePermissionActivity
import com.example.firstcode.other.actionStart

class ContentProviderPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_practice)

        findViewById<Button>(R.id.runtime_permission).setOnClickListener {
            actionStart(this, RuntimePermissionActivity::class.java)
        }

        findViewById<Button>(R.id.contact).setOnClickListener {
            actionStart(this, ContentProviderActivity::class.java)
        }
    }
}