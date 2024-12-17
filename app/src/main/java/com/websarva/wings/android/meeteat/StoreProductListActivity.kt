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
        // 各カードごとにフィルタリング
        val offerItems = productList.filter { it.image_url.contains("offer") } // オファリング
        val limitedTimeItems = productList.filter { it.image_url.contains("halloween") } // 期間限定
        val recommendedItems = productList.filter { it.image_url.contains("recommend") } // レコメンド
        val dominoSetItems = productList.filter { it.image_url.contains("domino_set") } // レコメンド
        val valuePizzaItems =
            productList.filter { it.image_url.contains("domino_valuepizza") } // レコメンド

        // リストをStore型に変換
        val offerStores = dbHelper.productsToStore(offerItems, "", "")
        val limitedTimeStores = dbHelper.productsToStore(limitedTimeItems, "", "")
        val recommendedStores = dbHelper.productsToStore(recommendedItems, "", "")
        val dominoSetStores = dbHelper.productsToStore(dominoSetItems, "", "")
        val valuePizzaStores = dbHelper.productsToStore(valuePizzaItems, "", "")

        // RecyclerViewの設定（オファリング）
        val offerRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_domino_offer)
        offerRecyclerView.layoutManager = LinearLayoutManager(this)
        offerRecyclerView.adapter = HorizontalItemAdapter(offerStores,{ clickedStore ->
            Log.d("StoreProductList", "Clicked limited time store: ${clickedStore.name}")
        }, isNavigationEnabled = false)

        /*
        { clickedStore ->
            // ImageListAdapterをカスタマイズ
            val imageAdapter = ImageListAdapter(
                clickedStore.images,
                { clickedImage ->
                    // 特定の画像のみ遷移
                    if (clickedImage.imageResId == R.drawable.img_domino_halloween_1) {
                        val intent = Intent(this, ProductDescriptionActivity::class.java)
                        intent.putExtra("product_name", clickedImage.name)
                        intent.putExtra("product_image", clickedImage.imageResId)
                        startActivity(intent)
                    } else {
                        Log.d("StoreProductList", "This product is not clickable.")
                    }
                },
                isNavigationEnabled = false // 他の画像では遷移不可にする
            )


         */


            // RecyclerViewの設定（期間限定）
            val limitedTimeRecyclerView =
                findViewById<RecyclerView>(R.id.recycler_view_domino_halloween)
            limitedTimeRecyclerView.layoutManager = LinearLayoutManager(this)
            limitedTimeRecyclerView.adapter = HorizontalItemAdapter(limitedTimeStores, { clickedStore ->
                Log.d("StoreProductList", "Clicked recommended store: ${clickedStore.name}")
            }, isNavigationEnabled = false)


            /*{ clickedStore ->
                Log.d("StoreProductList", "Clicked limited time store: ${clickedStore.name}")

                // ImageListAdapter を作成して、特定の画像クリック時の動作を定義
                val imageAdapter = ImageListAdapter(
                    clickedStore.images,
                    { clickedImage ->
                        // 画像が img_domino_halloween_1 の場合のみ遷移
                        if (clickedImage.imageResId == R.drawable.img_domino_halloween_1) {
                            val intent = Intent(this, ProductDescriptionActivity::class.java)
                            intent.putExtra("product_name", clickedImage.name)
                            intent.putExtra("product_image", clickedImage.imageResId)
                            startActivity(intent)
                        } else {
                            Log.d("StoreProductList", "This image is not clickable.")
                        }
                    },
                    isNavigationEnabled = false // 全体はクリック不可、特定画像のみ許可
                )

                // RecyclerViewのアダプターを再セット
                limitedTimeRecyclerView.adapter = imageAdapter
            }

             */

            // RecyclerViewの設定（おすすめ）
            val recommendedRecyclerView =
                findViewById<RecyclerView>(R.id.recycler_view_domino_recommend)
            recommendedRecyclerView.layoutManager = LinearLayoutManager(this)
            recommendedRecyclerView.adapter =
                HorizontalItemAdapter(recommendedStores, { clickedStore ->
                    Log.d("StoreProductList", "Clicked recommended store: ${clickedStore.name}")
                }, isNavigationEnabled = false)


            // RecyclerViewの設定（期間限定）
            val dominoSetRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_domino_set)
            dominoSetRecyclerView.layoutManager = LinearLayoutManager(this)
            dominoSetRecyclerView.adapter = HorizontalItemAdapter(dominoSetStores, { clickedStore ->
                Log.d("StoreProductList", "Clicked limited time store: ${clickedStore.name}")
            }, isNavigationEnabled = false)

            // RecyclerViewの設定（おすすめ）
            val valuePizzaRecyclerView =
                findViewById<RecyclerView>(R.id.recycler_view_domino_valuepizza)
            valuePizzaRecyclerView.layoutManager = LinearLayoutManager(this)
            valuePizzaRecyclerView.adapter =
                HorizontalItemAdapter(valuePizzaStores, { clickedStore ->
                    Log.d("StoreProductList", "Clicked recommended store: ${clickedStore.name}")
                }, isNavigationEnabled = false)
        }


        /*
        { clickedItem ->
            if (clickedItem.imageResId == R.drawable.img_dominopizza) { // 特定の画像のみ遷移
                val intent = Intent(this, PurchasePageActivity::class.java)
                intent.putExtra("product_name", clickedItem.name)
                intent.putExtra("product_image", clickedItem.imageResId)
                startActivity(intent)
            } else {
                Log.d("StoreProductListActivity", "This product is not clickable.")
            }
        } */

    }



