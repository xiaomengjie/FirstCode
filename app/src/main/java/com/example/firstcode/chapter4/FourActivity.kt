package com.example.firstcode.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.firstcode.R
import com.example.firstcode.chapter4.chat.ChatActivity
import com.example.firstcode.chapter4.listview.ListViewActivity
import com.example.firstcode.chapter4.recyclerview.RecyclerViewActivity
import com.example.firstcode.other.actionStart

class FourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)

//        val edit = findViewById<EditText>(R.id.text)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            edit.text.toString().let {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
//        }

//        val image = findViewById<ImageView>(R.id.image)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            image.setImageResource(R.drawable.img_2)
//        }

//        val progress = findViewById<ProgressBar>(R.id.progress)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            if (progress.isVisible){
//                progress.visibility = View.GONE
//            }else{
//                progress.visibility = View.VISIBLE
//            }
//            progress.progress += 10
//        }

        findViewById<Button>(R.id.button).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("This is Dialog")
                .setMessage("Something importance!")
                .setCancelable(false)
                .setPositiveButton("OK"){ dialog, whick -> dialog.dismiss()}
                .setNegativeButton("Cancel"){dialog, whick -> dialog.dismiss()}
                .show()
        }

        findViewById<Button>(R.id.list_view_activity).setOnClickListener {
            actionStart(this, ListViewActivity::class.java)
        }

        findViewById<Button>(R.id.recycler_view_activity).setOnClickListener {
            actionStart(this, RecyclerViewActivity::class.java)
        }
        findViewById<Button>(R.id.chat_activity).setOnClickListener {
            actionStart(this, ChatActivity::class.java)
        }
    }
}