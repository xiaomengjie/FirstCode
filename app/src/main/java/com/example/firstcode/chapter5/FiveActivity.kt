package com.example.firstcode.chapter5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.firstcode.R
import com.example.firstcode.chapter5.fragment.DynamicFragment
import com.example.firstcode.chapter5.lifecycle.LifecycleFragment
import com.example.firstcode.chapter5.news.NewsActivity
import com.example.firstcode.other.BaseActivity

class FiveActivity : BaseActivity() {

    private val lifecycleFragment by lazy { LifecycleFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)
        findViewById<Button>(R.id.add_fragment).setOnClickListener {
            replaceFragment(DynamicFragment())
        }

        findViewById<Button>(R.id.remove_fragment).setOnClickListener {
            removeFragment(lifecycleFragment)
        }

        findViewById<Button>(R.id.lifecycle_fragment).setOnClickListener {
            replaceFragment(lifecycleFragment)
        }

        findViewById<Button>(R.id.news_activity).setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
    }

    private fun <T: Fragment> replaceFragment(fragment: T) {
        val support = supportFragmentManager
        val transaction = support.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }

    private fun <T: Fragment> removeFragment(fragment: T) {
        val support = supportFragmentManager
        val transaction = support.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }
}