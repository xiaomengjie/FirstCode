package com.example.firstcode.chapter13.livedata

import androidx.lifecycle.*

class LiveDataViewModel(countReserved: Int): ViewModel() {

    private val _counter = MutableLiveData<Int>()

    val counter: LiveData<Int>
    get() = _counter

    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value?: 0
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }

    //只想暴露Person的name给调用者
    private val personLiveData = MutableLiveData<Person>()
    //当personLiveData中的数据发生变化，此时会执行map中的转换逻辑，
    //并将转换后的数据通知nameLiveData的观察者
    val nameLiveData: LiveData<String> = Transformations.map(personLiveData){ it.name }

    fun setData(){
        personLiveData.value = Person("data", 10)
    }

    // TODO: 2021/8/29 switchMap
    // 将调用其他对象获取的liveData，转换为同一个可观察的liveData
    // 用户观察userLiveData中的数据，当userIdLiveData发生变化时，userIdLiveData的switchMap方法就会执行
    // 并调用转换函数，转换成userLiveData
    private val userIdLiveData = MutableLiveData<Int>()
    val userLiveData: LiveData<User> = Transformations.switchMap(userIdLiveData){
        Repository.getUser(it)
    }
    fun getUser(userId: Int){
        userIdLiveData.value = userId
    }

    private val refreshLiveData = MutableLiveData<Any?>()
    val refreshResult = Transformations.switchMap(refreshLiveData){
        Repository.refresh()
    }
    fun refresh(){
        refreshLiveData.value = refreshLiveData.value
    }
}

class LiveDataFactory(private val countReserved: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LiveDataViewModel(countReserved) as T
    }

}

data class Person(val name: String, val age: Int)