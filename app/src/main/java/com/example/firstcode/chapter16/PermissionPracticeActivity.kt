package com.example.firstcode.chapter16

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstcode.R
import com.example.firstcode.databinding.ActivityPermissionPracticeBinding
import com.example.firstcode.other.toast
import com.permissionx.xiao.PermissionCallback
import com.permissionx.xiao.PermissionX
import java.lang.Exception

class PermissionPracticeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPermissionPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPermissionPracticeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.makeCall.setOnClickListener {
            PermissionX.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted){
                    call()
                }else{
                    "You denied $deniedList".toast()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}