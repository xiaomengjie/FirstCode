package com.permissionx.xiao

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

// TODO: 2021/9/3 0003 typealias给任意类型指定别名
//  这里是将(Boolean, List<String>) -> Unit指定别名为PermissionCallback
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment: Fragment() {

    private var callback: PermissionCallback? = null

    fun requestNow(block: PermissionCallback, vararg permissions: String){
        callback = block
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}