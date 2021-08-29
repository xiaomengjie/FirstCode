package com.example.firstcode.chapter12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.firstcode.R
import com.example.firstcode.other.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MaterialDesignActivity : AppCompatActivity() {
    private val drawerLayout: DrawerLayout by lazy {
        findViewById(R.id.drawerLayout)
    }

    private val navigationView: NavigationView by lazy {
        findViewById(R.id.navView)
    }

    private val fruits = listOf<Fruit>(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )
    private val fruitList = mutableListOf<Fruit>()

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.swipeRefresh) }

    private lateinit var adapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        navigationView.setCheckedItem(R.id.navCall)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Snackbar.make(it, "Data Deleted", Snackbar.LENGTH_SHORT).setAction("Undo"){
                showToast(this, "Data restored")
            }.show()
        }

        initFruits()
        adapter = FruitAdapter(this, fruitList)
        recyclerView.layoutManager = GridLayoutManager(this ,2)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500)
        swipeRefreshLayout.setOnRefreshListener {
            thread {
                Thread.sleep(2000)
                runOnUiThread{
                    initFruits()
                    swipeRefreshLayout.isRefreshing = false
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initFruits(): List<Fruit> {
        fruitList.clear()
        repeat(50){
            val random = fruits.indices.random()
            fruitList.add(fruits[random])
        }
        return fruitList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.backUp -> { showToast(this, "you click backup") }
            R.id.delete -> { showToast(this, "you click delete") }
            R.id.settings -> { showToast(this, "you click settings") }
            android.R.id.home -> { drawerLayout.openDrawer(GravityCompat.START) }
        }
        return true
    }
}