package com.example.firstcode.chapter4.custom

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.firstcode.R

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        findViewById<Button>(R.id.titleBack).setOnClickListener {
            if (context is Activity){
                context.finish()
            }
        }
        findViewById<Button>(R.id.titleEdit).setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
}