package com.example.firstcode.chapter9.multimedia

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import com.example.firstcode.R

class PlayVideoActivity : AppCompatActivity() {

    private val videoView by lazy { findViewById<VideoView>(R.id.videoView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)

        findViewById<Button>(R.id.play).setOnClickListener {
            if (!videoView.isPlaying){
                videoView.start()
            }
        }

        findViewById<Button>(R.id.pause).setOnClickListener {
            if (videoView.isPlaying){
                videoView.pause()
            }
        }

        findViewById<Button>(R.id.replay).setOnClickListener {
            if (videoView.isPlaying){
                videoView.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}