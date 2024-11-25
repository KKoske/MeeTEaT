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



        /*
        // 店舗データリスト（カード用）
        val  onRouteStores= listOf(
            ImageItem(R.drawable.img_dominopizza, "店舗1", "すぐ受け取り可能"),
            ImageItem(R.drawable.img_dominopizza, "店舗2", "準備中"),
            ImageItem(R.drawable.img_dominopizza, "店舗3", "受け取り可能")
        )

        // 仮の店舗データリスト（カード2用）
        val card2Stores = listOf(
            ImageItem(R.drawable.img_dominopizza, "店舗4", "すぐ受け取り可能"),
            ImageItem(R.drawable.img_dominopizza, "店舗5", "予約受付中"),
            ImageItem(R.drawable.img_dominopizza, "店舗6", "残りわずか")
        )
        */


// 各カードに表示する店舗をフィルタリング
        val onRouteStores = storeList.filter { it.id in listOf(1) }  // カード1用
        val postOrderStores = storeList.filter { it.id in listOf(2) }  // カード2用
        val chineseFoodStores = storeList.filter { it.id in listOf(3) }  // カード3用
        val japaneseFoodStores = storeList.filter { it.id in listOf(4) }  // カード3用
        val italianFoodStores = storeList.filter { it.id in listOf(5) }  // カード3用
        val cafeFoodStores = storeList.filter { it.id in listOf(6) }  // カード3用
        val fastFoodStores = storeList.filter { it.id in listOf(7) }  // カード3用
        val healthyFoodStores = storeList.filter { it.id in listOf(8) }  // カード3用


        Log.d("HomeScreenActivity", "onRouteStoresの内容: $onRouteStores")



// 必要なカードのリストをここに追加


        // 2. 各リサイクルビューにアダプターを設定
        //val recyclerViewMain = findViewById<RecyclerView>(R.id.recycler_view_main)
        //recyclerViewMain.layoutManager = LinearLayoutManager(this)
        //recyclerViewMain.adapter = HorizontalItemAdapter(storeList)

        val recyclerViewOnRoute = findViewById<RecyclerView>(R.id.recycler_view_on_route)
        recyclerViewOnRoute.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOnRoute.adapter = HorizontalItemAdapter(onRouteStores)

        val recyclerViewPostOrder = findViewById<RecyclerView>(R.id.recycler_view_post_order)
        recyclerViewPostOrder.layoutManager = LinearLayoutManager(this)
        recyclerViewPostOrder.adapter = HorizontalItemAdapter(postOrderStores)

        val recyclerViewChineseFood = findViewById<RecyclerView>(R.id.recycler_view_chinese_food)
        recyclerViewChineseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewChineseFood.adapter = HorizontalItemAdapter(chineseFoodStores)

        val recyclerViewJapaneseFood = findViewById<RecyclerView>(R.id.recycler_view_japanese_food)
        recyclerViewJapaneseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewJapaneseFood.adapter = HorizontalItemAdapter(japaneseFoodStores)

        val recyclerViewItalianFood = findViewById<RecyclerView>(R.id.recycler_view_italian_food)
        recyclerViewItalianFood.layoutManager = LinearLayoutManager(this)
        recyclerViewItalianFood.adapter = HorizontalItemAdapter(italianFoodStores)

        val recyclerViewCafeFood = findViewById<RecyclerView>(R.id.recycler_view_cafe_food)
        recyclerViewCafeFood.layoutManager = LinearLayoutManager(this)
        recyclerViewCafeFood.adapter = HorizontalItemAdapter(cafeFoodStores)

        val recyclerViewFastFood = findViewById<RecyclerView>(R.id.recycler_view_fast_food)
        recyclerViewFastFood.layoutManager = LinearLayoutManager(this)
        recyclerViewFastFood.adapter = HorizontalItemAdapter(fastFoodStores)

        val recyclerViewHealthyFood = findViewById<RecyclerView>(R.id.recycler_view_healthy_food)
        recyclerViewHealthyFood.layoutManager = LinearLayoutManager(this)
        recyclerViewHealthyFood.adapter = HorizontalItemAdapter(healthyFoodStores)
    }
}
