package com.websarva.wings.android.meeteat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var dbHelper: AddDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // データベースから全ての店舗データを取得
        dbHelper = AddDatabaseHelper(this)
        val storeList = dbHelper.getAllStores()

// 各カードに表示する店舗をフィルタリング
        val onRouteStores = storeList.filter { it.id in listOf(16, 15) }  // カード1用
        val postOrderStores = storeList.filter { it.id in listOf(14, 13) }  // カード2用
        val chineseFoodStores = storeList.filter { it.id in listOf(12, 11) }  // カード3用
// 必要なカードのリストをここに追加


        // 2. 各リサイクルビューにアダプターを設定
        //val recyclerViewMain = findViewById<RecyclerView>(R.id.recycler_view_main)
        //recyclerViewMain.layoutManager = LinearLayoutManager(this)
        //recyclerViewMain.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewOnRoute = findViewById<RecyclerView>(R.id.recycler_view_on_route)
        recyclerViewOnRoute.layoutManager = LinearLayoutManager(this)
        recyclerViewOnRoute.adapter = HorizontalItemAdapter(onRouteStores)

        val recyclerViewPostOrder = findViewById<RecyclerView>(R.id.recycler_view_post_order)
        recyclerViewPostOrder.layoutManager = LinearLayoutManager(this)
        recyclerViewPostOrder.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewChineseFood = findViewById<RecyclerView>(R.id.recycler_view_chinese_food)
        recyclerViewChineseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewChineseFood.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewJapaneseFood = findViewById<RecyclerView>(R.id.recycler_view_japanese_food)
        recyclerViewJapaneseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewJapaneseFood.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewItalianFood = findViewById<RecyclerView>(R.id.recycler_view_italian_food)
        recyclerViewItalianFood.layoutManager = LinearLayoutManager(this)
        recyclerViewItalianFood.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewCafeFood = findViewById<RecyclerView>(R.id.recycler_view_cafe_food)
        recyclerViewCafeFood.layoutManager = LinearLayoutManager(this)
        recyclerViewCafeFood.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewFastFood = findViewById<RecyclerView>(R.id.recycler_view_fast_food)
        recyclerViewFastFood.layoutManager = LinearLayoutManager(this)
        recyclerViewFastFood.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewHealthyFood = findViewById<RecyclerView>(R.id.recycler_view_healthy_food)
        recyclerViewHealthyFood.layoutManager = LinearLayoutManager(this)
        recyclerViewHealthyFood.adapter = HorizontalItemAdapter(storeList)
    }
}
