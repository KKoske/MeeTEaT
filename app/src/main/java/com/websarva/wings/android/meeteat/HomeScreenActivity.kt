package com.websarva.wings.android.meeteat

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject
import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONException


class HomeScreenActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var dbHelper: AddDatabaseHelper
    private lateinit var map: GoogleMap



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            // 権限がある場合、現在地を取得
            getCurrentLocation()
        } else {
            // 権限がない場合、許可を求める
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        }
    }

    //ここのエラーは権限確認がなされていないために起こるが、上のcheckLocationPermission()によってすでに権限はチェックされているので、そのままでも大丈夫です。
    private fun getCurrentLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                Log.d("CurrentLocation", "現在地: $currentLatLng") // 取得した現在地をログ出力
                map.addMarker(MarkerOptions().position(currentLatLng).title("現在地"))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))

                // 次のステップ: 設定地点とのルート表示
                val destinationLatLng = LatLng(35.658581, 139.745433)
                drawRoute(currentLatLng, destinationLatLng) // 東京タワーの座標
                adjustCameraToRoute(currentLatLng, destinationLatLng)
            } else {
                Log.e("LocationError", "現在地が取得できません") // 現在地が null の場合
            }
        }.addOnFailureListener {
            Log.e("LocationError", "現在地取得に失敗しました: ${it.message}") // 取得失敗時のログ
        }
    }

    private fun adjustCameraToRoute(start: LatLng, end: LatLng) {
        // LatLngBounds.Builder を作成
        val boundsBuilder = LatLngBounds.Builder()

        // 現在地と設定地点を追加
        boundsBuilder.include(start) // 現在地
        boundsBuilder.include(end)   // 目的地

        // 範囲（LatLngBounds）を構築
        val bounds = boundsBuilder.build()

        // マップのカメラを範囲全体に収める
        val padding = 80 // 地図の端に余白を付ける（ピクセル単位）
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        map.animateCamera(cameraUpdate) // カメラをアニメーションで移動
    }


    private fun setMarker(location: LatLng) {
        map.addMarker(MarkerOptions().position(location).title("設定地点"))
    }

    private fun drawRoute(origin: LatLng, destination: LatLng) {
        val url ="https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=${origin.latitude},${origin.longitude}" +
                "&destination=${destination.latitude},${destination.longitude}" +
                "&key=AIzaSyBXVO3tIpmXySd5PxV71uPF-IadDEYPrBM"

        Log.d("RequestURL", url) // リクエスト URL をログ出力


        // APIリクエストを実行
        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, { response ->
            Log.d("APIResponse", response) // API のレスポンスをログ出力

            val jsonObject = JSONObject(response)
            val status = jsonObject.getString("status")
            if (status != "OK") {
                Log.e("RouteError", "API エラー: $status") // API のステータスがエラーの場合
                return@StringRequest
            }

            val path = parseRoute(response)
            if (path.isEmpty()) {
                Log.e("RouteError", "ルートデータが空です") // 空のルートの場合のログ
            } else {
                map.addPolyline(PolylineOptions().addAll(path).width(10f).color(Color.RED))
            }
        }, {
            Log.e("RouteError", "経路情報の取得に失敗しました: ${it.message}") // リクエスト失敗時のログ
        })
        requestQueue.add(request)
    }

    private fun parseRoute(response: String): List<LatLng> {
        try {
            val jsonObject = JSONObject(response)
            val routes = jsonObject.getJSONArray("routes")

            // routes 配列が空の場合の処理
            if (routes.length() == 0) {
                Log.e("RouteError", "ルートが見つかりませんでした") // routes が空の場合
                return emptyList()
            }
            Log.d("RoutesData", "routes 配列: $routes") // routes 配列のデータをログ出力


            val legs = routes.getJSONObject(0).getJSONArray("legs")
            val steps = legs.getJSONObject(0).getJSONArray("steps")

        val path = mutableListOf<LatLng>()
        for (i in 0 until steps.length()) {
            val polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points")
            path.addAll(PolyUtil.decode(polyline))
        }
        return path
        } catch (e: JSONException) {
            Log.e("ParseError", "レスポンスの解析に失敗しました: ${e.message}") // JSON 解析失敗時のログ
            return emptyList()
        }
    }








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


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkLocationPermission()

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
                    ImageItem(R.drawable.img_dominopizza, "ドミノピザ", "すぐ受け取り"),
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
        recyclerViewonRouteStore.adapter = HorizontalItemAdapter(onRouteStores, { clickedStore ->
            Log.d("HomeScreenActivity", "Clicked Store ID: ${clickedStore.id}")
            Log.d("HomeScreenActivity", "Starting StoreProductListActivity with store_id: ${clickedStore.id}")

            val intent = Intent(this, StoreProductListActivity::class.java)
            Log.d("HomeScreenActivity", "Navigating to StoreProductListActivity with store_id: ${clickedStore.id}")
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)


        Log.d("AdapterDebug", "LayoutManager is set to ${recyclerViewonRouteStore.layoutManager}")
        Log.d("AdapterDebug", "LayoutManager is set to ${recyclerViewonRouteStore.layoutManager}")
        Log.d("AdapterDebug", "PagerSnapHelper attached: ${recyclerViewonRouteStore.onFlingListener != null}")
        // RecyclerView に CustomItemDecoration を追加
        recyclerViewonRouteStore.addItemDecoration(CustomItemDecoration(0)) // 16dpのスペースを設定





        val recyclerViewPostOrder = findViewById<RecyclerView>(R.id.recycler_view_post_order)
        recyclerViewPostOrder.layoutManager = LinearLayoutManager(this)
        recyclerViewPostOrder.adapter = HorizontalItemAdapter(postOrderStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)

        val recyclerViewChineseFood = findViewById<RecyclerView>(R.id.recycler_view_chinese_food)
        recyclerViewChineseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewChineseFood.adapter = HorizontalItemAdapter(chineseFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)


        val recyclerViewJapaneseFood = findViewById<RecyclerView>(R.id.recycler_view_japanese_food)
        recyclerViewJapaneseFood.layoutManager = LinearLayoutManager(this)
        recyclerViewJapaneseFood.adapter = HorizontalItemAdapter(japaneseFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)

        val recyclerViewItalianFood = findViewById<RecyclerView>(R.id.recycler_view_italian_food)
        recyclerViewItalianFood.layoutManager = LinearLayoutManager(this)
        recyclerViewItalianFood.adapter = HorizontalItemAdapter(italianFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)

        val recyclerViewCafeFood = findViewById<RecyclerView>(R.id.recycler_view_cafe_food)
        recyclerViewCafeFood.layoutManager = LinearLayoutManager(this)
        recyclerViewCafeFood.adapter = HorizontalItemAdapter(cafeFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)

        val recyclerViewFastFood = findViewById<RecyclerView>(R.id.recycler_view_fast_food)
        recyclerViewFastFood.layoutManager = LinearLayoutManager(this)
        recyclerViewFastFood.adapter = HorizontalItemAdapter(fastFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)
        }, isNavigationEnabled = true)

        val recyclerViewHealthyFood = findViewById<RecyclerView>(R.id.recycler_view_healthy_food)
        recyclerViewHealthyFood.layoutManager = LinearLayoutManager(this)
        recyclerViewHealthyFood.adapter = HorizontalItemAdapter(healthyFoodStores, { clickedStore ->
            val intent = Intent(this, StoreProductListActivity::class.java)
            intent.putExtra("store_id", clickedStore.id) // クリックされた店舗のIDを渡す
            startActivity(intent)

        }, isNavigationEnabled = true)





    }

    private fun onStoreItemClick(storeId: Int) {
        // StoreProductListActivity に遷移
        val intent = Intent(this, StoreProductListActivity::class.java)
        intent.putExtra("store_id", storeId) // 選択された店舗のIDを渡す
        startActivity(intent)
    }



}
