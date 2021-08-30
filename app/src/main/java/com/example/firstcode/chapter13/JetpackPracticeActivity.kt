package com.example.firstcode.chapter13

import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter13.lifecycle.LifecycleObserverActivity
import com.example.firstcode.chapter13.livedata.LiveDataActivity
import com.example.firstcode.chapter13.room.RoomActivity
import com.example.firstcode.chapter13.viewmodel.ViewModelActivity
import com.example.firstcode.other.*

class JetpackPracticeActivity : BaseAbstractActivity() {

    override fun getViewLayout(): Int {
        return R.layout.activity_jetpack_practice
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.view_model to getString(R.string.view_model),
            R.string.lifecycle to getString(R.string.lifecycle),
            R.string.live_data to getString(R.string.live_data),
            R.string.room to getString(R.string.room)
        )
    }

    override fun onClickListener(view: View) {
        when(view.id){
            R.string.view_model -> {
                startActivity<ViewModelActivity>(this)
            }
            R.string.lifecycle -> {
                startActivity<LifecycleObserverActivity>(this)
            }
            R.string.live_data -> {
                startActivity<LiveDataActivity>(this)
            }
            R.string.room -> {
                startActivity<RoomActivity>(this)
            }
        }
    }
}