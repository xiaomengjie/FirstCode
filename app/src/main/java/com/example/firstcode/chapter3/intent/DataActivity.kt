package com.example.firstcode.chapter3.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.firstcode.R

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        intent.getStringExtra("data")?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        val result = findViewById<Button>(R.id.intentDataResult)
        result.setOnClickListener {
            setResult(101, Intent().apply {
                putExtra("result", "result")
            })
            finish()
        }
    }


    override fun onBackPressed() {
        setResult(101, Intent().apply {
            putExtra("result", "result")
        })
        super.onBackPressed()
    }
}