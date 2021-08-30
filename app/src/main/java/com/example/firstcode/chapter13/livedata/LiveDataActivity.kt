package com.example.firstcode.chapter13.livedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.firstcode.R

class LiveDataActivity : AppCompatActivity() {

    private val infoText: TextView by lazy {
        findViewById(R.id.infoText)
    }

    private val plusOneBtn: Button by lazy {
        findViewById(R.id.plusOneBtn)
    }

    private val clearText: Button by lazy {
        findViewById(R.id.clearText)
    }

    private val map: Button by lazy {
        findViewById(R.id.map)
    }

    private val switchMap: Button by lazy {
        findViewById(R.id.switchMap)
    }

    private lateinit var liveDataViewModel: LiveDataViewModel

    private val sharePreferences: SharedPreferences by lazy {
        getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        liveDataViewModel = ViewModelProvider(this, LiveDataFactory(
            sharePreferences.getInt("count", 0)
        )).get(LiveDataViewModel::class.java)

        liveDataViewModel.counter.observe(this){
            infoText.text = it.toString()
        }
        plusOneBtn.setOnClickListener {
            liveDataViewModel.plusOne()
        }
        clearText.setOnClickListener {
            liveDataViewModel.clear()
        }

        // TODO: 2021/8/29 map
        liveDataViewModel.nameLiveData.observe(this){
            infoText.text = it
        }
        map.setOnClickListener {
            liveDataViewModel.setData()
        }

        // TODO: 2021/8/29 switchMap
        liveDataViewModel.userLiveData.observe(this){
            infoText.text = it.userId.toString()
        }
        switchMap.setOnClickListener {
            liveDataViewModel.getUser((0 .. 5000).random())
        }
    }

    override fun onPause() {
        super.onPause()
        sharePreferences.edit {
            putInt("count", liveDataViewModel.counter.value?: 0)
        }
    }
}