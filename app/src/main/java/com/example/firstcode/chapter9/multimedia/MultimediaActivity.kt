package com.example.firstcode.chapter9.multimedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.other.actionStart

class MultimediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia)

        findViewById<Button>(R.id.playMusic).setOnClickListener {
            actionStart(this, PlayMusicActivity::class.java)
        }

        findViewById<Button>(R.id.playVideo).setOnClickListener {
            actionStart(this, PlayVideoActivity::class.java)
        }
    }
}