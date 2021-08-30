package com.example.firstcode.chapter13.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.firstcode.R
import com.example.firstcode.chapter13.lifecycle.LifecycleObserverActivity
import com.example.firstcode.chapter13.livedata.LiveDataActivity
import com.example.firstcode.chapter13.viewmodel.ViewModelActivity
import com.example.firstcode.other.*
import kotlin.concurrent.thread

class RoomActivity : BaseAbstractActivity() {

    private val personDao: PersonDao by lazy {
        PersonDatabase.getDatabase(this).personDao()
    }

    override fun getViewLayout(): Int {
        return R.layout.activity_room
    }

    override fun initButtonLayout(): ButtonListLayout? {
        return findViewById(R.id.buttonList)
    }

    override fun initButtonBean(): List<ButtonBean> {
        return listOf(
            R.string.insert_data to getString(R.string.insert_data),
            R.string.query_data to getString(R.string.query_data),
            R.string.delete_data to getString(R.string.delete_data),
            R.string.update_data to getString(R.string.update_data),
            R.string.delete_data_use_sql to getString(R.string.delete_data_use_sql)
        )
    }

    private val personOne = Person("one", 18)
    private val personTwo = Person("two", 20)
    // TODO: 2021/8/31 Room不允许在主线程进行数据操作
    override fun onClickListener(view: View) {
        when(view.id){
            R.string.insert_data -> {
                thread {
                    personDao.insertPerson(personOne)
                    personDao.insertPerson(personTwo)
                }
            }
            R.string.query_data -> {
                thread {
                    personDao.loadAllPerson().forEach {
                        Log.e(TAG, "$it")
                    }
                }
            }
            R.string.delete_data -> {
                thread {
                    personDao.deletePerson(personOne)
                }
            }
            R.string.update_data -> {
                thread {
                    personTwo.age = 30
                    personDao.updatePerson(personTwo)
                }
            }
            R.string.delete_data_use_sql -> {
                thread {
                    personDao.deleteAllPerson()
                }
            }
        }
    }
}