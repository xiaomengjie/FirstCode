package com.example.firstcode.chapter12

import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter11.network.NetworkActivity
import com.example.firstcode.chapter11.network.ParseActivity
import com.example.firstcode.chapter11.retrofit.RetrofitActivity
import com.example.firstcode.chapter11.webview.WebViewActivity
import com.example.firstcode.other.BaseAbstractActivity
import com.example.firstcode.other.ButtonBean
import com.example.firstcode.other.ButtonListLayout
import com.example.firstcode.other.*

class MaterialDesignPracticeActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_material_design_practice
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.material_design to getString(R.string.material_design)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.material_design -> {
                startActivity<MaterialDesignActivity>(this)
            }
        }
    }
}