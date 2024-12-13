package com.websarva.wings.android.meeteat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var dbHelper: AddDatabaseHelper

    //カード内の画像同士の間隔調節
    fun RecyclerView.addHorizontalSpacing(spacing: Int) {
        val itemDecoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: android.graphics.Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.right = spacing
            }
        }
        addItemDecoration(itemDecoration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // データベースから全ての店舗データを取得
        dbHelper = AddDatabaseHelper(this)
        val storeList = dbHelper.getAllStores()

        //onRouteStoreカード用　グリットなどを別枠で定義している
        val onRouteStores = listOf(
            Store(
                id = 16,
                name = "ドミノピザ",
                address = "東京都渋谷区123-456",
                image_url = "https://example.com/domino.jpg",
                images = listOf(
                    ImageItem(R.drawable.img_dominopizza, "店舗1", "すぐ受け取り"),
                    ImageItem(R.drawable.img_restaurant_yoshinoya, "店舗2", "準備中"),
                    ImageItem(R.drawable.img_ousho, "店舗3", "受け取り可能"),
                    ImageItem(R.drawable.img_alba, "店舗4", "すぐ受け取り"),
                    ImageItem(R.drawable.img_doutal_2, "店舗5", "予約受付中"),
                    ImageItem(R.drawable.img_restaurant_cocoichi, "店舗6", "残りわずか"),
                    ImageItem(R.drawable.img_denen, "店舗7", "受け取り可能"),
                    ImageItem(R.drawable.img_macdonalds, "店舗8", "準備中"),
                    ImageItem(R.drawable.img_nattou, "店舗9", "すぐ受け取り"),
                    ImageItem(R.drawable.img_restaurant_cocoichi, "店舗6", "残りわずか"),
                    ImageItem(R.drawable.img_denen, "店舗7", "受け取り可能"),
                    ImageItem(R.drawable.img_macdonalds, "店舗8", "準備中"),
                    ImageItem(R.drawable.img_nattou, "店舗9", "すぐ受け取り"),
                    ImageItem(R.drawable.img_denen, "店舗7", "受け取り可能"),
                    ImageItem(R.drawable.img_macdonalds, "店舗8", "準備中"),
                    ImageItem(R.drawable.img_nattou, "店舗9", "すぐ受け取り"),
                    ImageItem(R.drawable.img_macdonalds, "店舗8", "準備中"),
                    ImageItem(R.drawable.img_nattou, "店舗9", "すぐ受け取り")
                ),
                isGrid = true // グリッド表示用フラグ
            )
        )


        Log.d("HomeScreenActivity", "onRouteStores内容: $onRouteStores")





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
        val postOrderStores = storeList.filter { it.id in listOf(2) }  // カード2用
        val chineseFoodStores = storeList.filter { it.id in listOf(3) }  // カード3用
        val japaneseFoodStores = storeList.filter { it.id in listOf(4) }  // カード3用
        val italianFoodStores = storeList.filter { it.id in listOf(5) }  // カード3用
        val cafeFoodStores = storeList.filter { it.id in listOf(6) }  // カード3用
        val fastFoodStores = storeList.filter { it.id in listOf(7) }  // カード3用
        val healthyFoodStores = storeList.filter { it.id in listOf(8) }  // カード3用


        Log.d("HomeScreenActivity", "onRouteStores size: ${onRouteStores.size}")

        Log.d("HomeScreenActivity", "onRouteStoresの内容: $onRouteStores")


// 必要なカードのリストをここに追加


        // 2. 各リサイクルビューにアダプターを設定
        //val recyclerViewMain = findViewById<RecyclerView>(R.id.recycler_view_main)
        //recyclerViewMain.layoutManager = LinearLayoutManager(this)
        //recyclerViewMain.adapter = HorizontalItemAdapter(storeList)

        /*
        val recyclerViewOnRoute = findViewById<RecyclerView>(R.id.recycler_view_on_route)
        val gridLayoutManager = GridLayoutManager(this, 3) // 3列のグリッドを設定
        recyclerViewOnRoute.layoutManager = gridLayoutManager
        recyclerViewOnRoute.adapter = HorizontalItemAdapter(onRouteStores)
        // PagerSnapHelper を適用
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewOnRoute)

// 確認用ログ
        Log.d("DebugCheck", "GridLayoutManager set for recyclerViewOnRoute")
        Log.d("DebugCheck", "onRouteStores: ${onRouteStores.size}")
        Log.d("ParentRecyclerView", "Adapter Item Count: ${onRouteStores.size}")
        Log.d("RecyclerViewCheck", "LayoutManager: ${recyclerViewOnRoute.layoutManager}")
        recyclerViewOnRoute.viewTreeObserver.addOnGlobalLayoutListener {
            Log.d("DebugCheck", "RecyclerView dimensions: width=${recyclerViewOnRoute.width}, height=${recyclerViewOnRoute.height}")
            Log.d("DebugCheck", "RecyclerView child count: ${recyclerViewOnRoute.childCount}")
        }
         */


        val recyclerViewonRouteStore = findViewById<RecyclerView>(R.id.recycler_view_on_route)
        recyclerViewonRouteStore.layoutManager = LinearLayoutManager(this)
        recyclerViewonRouteStore.adapter = HorizontalItemAdapter(onRouteStores) { clickedStore ->
            Log.d("HomeScreenActivity", "Clicked Store ID: ${clickedStore.id}")
            Log.d("HomeScreenActivity", "Starting StoreProductListActivity with store_id: ${clickedStore.id}")

            val intent = Intent(this, StoreProductListActivity::class.java)
            Log.d("HomeScreenActivity", "Navigating to StoreProductListActivity with store_id: ${clickedStore.id}")
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }


        Log.d("AdapterDebug", "LayoutManager is set to ${recyclerViewonRouteStore.layoutManager}")
        Log.d("AdapterDebug", "LayoutManager is set to ${recyclerViewonRouteStore.layoutManager}")
        Log.d("AdapterDebug", "PagerSnapHelper attached: ${recyclerViewonRouteStore.onFlingListener != null}")
        // RecyclerView に CustomItemDecoration を追加
        recyclerViewonRouteStore.addItemDecoration(CustomItemDecoration(0)) // 16dpのスペースを設定





        val recyclerViewPostOrder = findViewById<RecyclerView>(R.id.recycler_view_post_order)
        recyclerViewPostOrder.layoutManager = LinearLayoutManager(this)
        recyclerViewPostOrder.adapter = HorizontalItemAdapter(postOrderStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }

        val recyclerViewChineseFood = findViewById<RecyclerView>(R.id.recycler_view_chinese_food)
        recyclerViewChineseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewChineseFood.adapter = HorizontalItemAdapter(chineseFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }


        val recyclerViewJapaneseFood = findViewById<RecyclerView>(R.id.recycler_view_japanese_food)
        recyclerViewJapaneseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewJapaneseFood.adapter = HorizontalItemAdapter(japaneseFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }

        val recyclerViewItalianFood = findViewById<RecyclerView>(R.id.recycler_view_italian_food)
        recyclerViewItalianFood.layoutManager = LinearLayoutManager(this)
        recyclerViewItalianFood.adapter = HorizontalItemAdapter(italianFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }

        val recyclerViewCafeFood = findViewById<RecyclerView>(R.id.recycler_view_cafe_food)
        recyclerViewCafeFood.layoutManager = LinearLayoutManager(this)
        recyclerViewCafeFood.adapter = HorizontalItemAdapter(cafeFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }

        val recyclerViewFastFood = findViewById<RecyclerView>(R.id.recycler_view_fast_food)
        recyclerViewFastFood.layoutManager = LinearLayoutManager(this)
        recyclerViewFastFood.adapter = HorizontalItemAdapter(fastFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }

        val recyclerViewHealthyFood = findViewById<RecyclerView>(R.id.recycler_view_healthy_food)
        recyclerViewHealthyFood.layoutManager = LinearLayoutManager(this)
        recyclerViewHealthyFood.adapter = HorizontalItemAdapter(healthyFoodStores) { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)

        }





    }

    private fun onStoreItemClick(storeId: Int) {
        // StoreProductListActivity に遷移
        val intent = Intent(this, StoreProductListActivity::class.java)
        intent.putExtra("store_id", storeId) // 選択された店舗のIDを渡す
        startActivity(intent)
    }



}
