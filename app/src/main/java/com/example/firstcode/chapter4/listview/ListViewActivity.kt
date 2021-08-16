package com.example.firstcode.chapter4.listview

import android.os.Bundle
import android.widget.ListView
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.other.showToast

class ListViewActivity : BaseActivity() {

    private val data = listOf<String>("Apple", "Banana", "Orange", "Watermelon",
    "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",)

    private val fruitList = mutableListOf<FruitBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val listView = findViewById<ListView>(R.id.list_view)

//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)

        initFruits()
        val adapter = FruitAdapter(this, R.layout.fruit_item_vertical, fruitList)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val fruitBean = fruitList[position]
            showToast(this, fruitBean.name)
        }
    }

    private fun initFruits() {
        repeat(2){
            fruitList.add(FruitBean("Apple", R.drawable.apple_pic))
            fruitList.add(FruitBean("Banana", R.drawable.banana_pic))
            fruitList.add(FruitBean("Orange", R.drawable.orange_pic))
            fruitList.add(FruitBean("Watermelon", R.drawable.watermelon_pic))
            fruitList.add(FruitBean("Pear", R.drawable.pear_pic))
            fruitList.add(FruitBean("Grape", R.drawable.grape_pic))
            fruitList.add(FruitBean("Pineapple", R.drawable.pineapple_pic))
            fruitList.add(FruitBean("Strawberry", R.drawable.strawberry_pic))
            fruitList.add(FruitBean("Cherry", R.drawable.cherry_pic))
            fruitList.add(FruitBean("Mango", R.drawable.mango_pic))
        }
    }
}