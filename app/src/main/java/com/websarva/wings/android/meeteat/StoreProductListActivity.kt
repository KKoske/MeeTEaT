package com.websarva.wings.android.meeteat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StoreProductListActivity : AppCompatActivity() {

    private lateinit var dbHelper: AddDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_product_list)

        // データベースヘルパーを初期化
        dbHelper = AddDatabaseHelper(this)



        // クリックされたstore_idをIntentから取得
        val storeId = intent.getIntExtra("store_id", -1) // デフォルト値 -1
        Log.d("StoreProductList", "Received store_id: $storeId")
        if (storeId == -1) {
            // store_idが取得できない場合のエラーハンドリング
            Log.e("StoreItemListActivity", "store_idが渡されていません")
            finish() // アクティビティを終了
            return
        }
        val storeName = "" // 店舗名を指定
        val storeAddress = "" // 店舗住所を指定
        val productList = dbHelper.getAllItems(storeId) // 商品データを取得
        Log.d("StoreProductList", "Product List: $productList")
        val imageItems = dbHelper.productsToStore(productList, storeName, storeAddress) // product型をstore型に変換

        // 商品リストを RecyclerView に表示するための設定
        val dominoOffer = findViewById<RecyclerView>(R.id.recycler_view_domino_offer) // XML に定義された RecyclerView の ID
        dominoOffer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // 横スクロール用のレイアウトマネージャー
        dominoOffer.adapter = HorizontalItemAdapter(imageItems){ _ -> }

    }
}


