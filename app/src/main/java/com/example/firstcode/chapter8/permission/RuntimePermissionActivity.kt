package com.example.firstcode.chapter8.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.firstcode.R
import com.example.firstcode.other.showToast

class RuntimePermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime_permission)

        findViewById<Button>(R.id.make_call).setOnClickListener {
            //打电话功能，CALL_PHONE 属于危险权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }else{
                call()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call()
                }else{
                    showToast(this, "you denial permission")
                }
            }
        }
    }

    private fun call(){
        try {
            startActivity(Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:10086")
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}