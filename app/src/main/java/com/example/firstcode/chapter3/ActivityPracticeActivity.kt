package com.example.firstcode.chapter3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.R
import com.example.firstcode.chapter3.intent.DataActivity
import com.example.firstcode.chapter3.launchmode.LaunchModeActivity
import com.example.firstcode.chapter3.lifecycle.LifecycleActivity

class ActivityPracticeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity_practice)
        savedInstanceState?.getString("data")

        val toast = findViewById<Button>(R.id.showToast)
        toast.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        }

        val finish = findViewById<Button>(R.id.finishActivity)
        finish.setOnClickListener {
            finish()
        }

        val intent = findViewById<Button>(R.id.intentActivity)
        intent.setOnClickListener {
//            val intent = Intent(this, IntentActivity::class.java)
//            startActivity(intent)

            val intent = Intent().apply {
                action = "${packageName}.action"
                addCategory("${packageName}.category")
            }
            startActivity(intent)
        }

        val browser = findViewById<Button>(R.id.intentBrowser)
        browser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.baidu.com")
            }
            startActivity(intent)
        }

        val data = findViewById<Button>(R.id.intentData)
        data.setOnClickListener {
            val intent = Intent(this, DataActivity::class.java).apply {
                putExtra("data", TAG)
            }
            startActivity(intent)
        }

        val result = findViewById<Button>(R.id.intentDataResult)
        result.setOnClickListener {
            val intent = Intent(this, DataActivity::class.java)
            startActivityForResult(intent, 100)
        }

        val lifecycle = findViewById<Button>(R.id.lifecycleActivity)
        lifecycle.setOnClickListener {
            val intent = Intent(this, LifecycleActivity::class.java)
            startActivity(intent)
        }

        val launchMode = findViewById<Button>(R.id.launchMode)
        launchMode.setOnClickListener {
            val intent = Intent(this, LaunchModeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == 101){
            Toast.makeText(this, data?.getStringExtra("result"), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.remove -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("data", "data")
    }

    companion object{
        private fun actionStart(context: Context, name: String, age: Int){
            context.startActivity(Intent(context, ActivityPracticeActivity::class.java).apply {
                putExtra("name", name)
                putExtra("age", age)
            })
        }
    }
}