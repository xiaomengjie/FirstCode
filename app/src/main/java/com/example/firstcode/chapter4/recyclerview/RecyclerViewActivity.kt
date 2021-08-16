package com.example.firstcode.chapter4.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.firstcode.R
import com.example.firstcode.chapter4.listview.FruitBean
import com.example.firstcode.other.BaseActivity
import java.lang.StringBuilder

class RecyclerViewActivity : BaseActivity() {

    private val fruitList = mutableListOf<FruitBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //上下滑动
//        initFruits()
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = VerticalFruitAdapter(fruitList)
//        recyclerView.adapter = adapter

        //水平滑动
//        initFruits()
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        val adapter = HorizontalFruitAdapter(fruitList)
//        recyclerView.adapter = adapter

        //网格
//        initFruits()
//        recyclerView.layoutManager = GridLayoutManager(this, 3)
//        val adapter = GridFruitAdapter(fruitList)
//        recyclerView.adapter = adapter

        //瀑布流
        initRandomFruits()
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val adapter = StaggeredGridFruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(4){
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

    private fun initRandomFruits() {
        repeat(4){
            fruitList.add(FruitBean(getRandomLengthString("Apple"), R.drawable.apple_pic))
            fruitList.add(FruitBean(getRandomLengthString("Banana"), R.drawable.banana_pic))
            fruitList.add(FruitBean(getRandomLengthString("Orange"), R.drawable.orange_pic))
            fruitList.add(FruitBean(getRandomLengthString("Watermelon"), R.drawable.watermelon_pic))
            fruitList.add(FruitBean(getRandomLengthString("Pear"), R.drawable.pear_pic))
            fruitList.add(FruitBean(getRandomLengthString("Grape"), R.drawable.grape_pic))
            fruitList.add(FruitBean(getRandomLengthString("Pineapple"), R.drawable.pineapple_pic))
            fruitList.add(FruitBean(getRandomLengthString("Strawberry"), R.drawable.strawberry_pic))
            fruitList.add(FruitBean(getRandomLengthString("Cherry"), R.drawable.cherry_pic))
            fruitList.add(FruitBean(getRandomLengthString("Mango"), R.drawable.mango_pic))
        }
    }

    private fun getRandomLengthString(string: String): String{
        val n = (1 .. 20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(string)
        }
        return builder.toString()
    }
}