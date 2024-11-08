package com.websarva.wings.android.meeteat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.AddDatabaseHelper

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var dbHelper: AddDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        dbHelper = AddDatabaseHelper(this)
        val stores = dbHelper.getAllStores() //これはデータを取得して使用する例


        //ルート上カードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_on_route)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter


        //過去の注文カードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_post_order)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //中華カードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_chinese_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //和食カードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_japanese_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //イタリアンカードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_italian_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //カフェカードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_cafe_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //ファストフードカードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_fast_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

        //ヘルシーカードのリサイクルビュー〜アダプターの定義
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_healthy_food)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val databaseHelper = AddDatabaseHelper(this)
        val storeList = databaseHelper.getAllStores()
        val adapter = HorizontalItemAdapter(storeList)
        recyclerView.adapter = adapter

    }
}