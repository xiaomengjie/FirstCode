package com.example.firstcode.chapter13.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.firstcode.R

class ViewModelActivity : AppCompatActivity() {

    private val infoText: TextView by lazy {
        findViewById(R.id.infoText)
    }

    private val plusOneBtn: Button by lazy {
        findViewById(R.id.plusOneBtn)
    }

    private val clearText: Button by lazy {
        findViewById(R.id.clearText)
    }

    private lateinit var dataViewModel: DataViewModel

    private val sharePreferences: SharedPreferences by lazy {
        getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        dataViewModel = ViewModelProvider(this, DataViewModelFactory(
            sharePreferences.getInt("count", 0)
        )).get(DataViewModel::class.java)

        plusOneBtn.setOnClickListener {
            dataViewModel.counter++
            refreshCounter()
        }
        clearText.setOnClickListener {
            dataViewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }

    private fun refreshCounter() {
        infoText.text = dataViewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sharePreferences.edit {
            putInt("count", dataViewModel.counter)
        }
    }
}