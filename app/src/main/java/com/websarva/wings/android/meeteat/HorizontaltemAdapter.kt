package com.websarva.wings.android.meeteat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView



class HorizontalItemAdapter(private val itemList: List<Store>,private val onItemClick: (Store) -> Unit ) : RecyclerView.Adapter<HorizontalItemAdapter.ItemViewHolder>() {
    init {
        Log.d("HorizontalItemAdapter", "Adapter initialized")
    }


    // ビューホルダークラス
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //この下の３行の定義、コメントアウトを外すとhomescreen画面に行かないループが発生する。おそらく、nullからのリサイクルビューが何度も読み込まれようとしてエラーになる問題で、繰り返すhomescreenが読み込まれて。エミュレーターが落ちる
        //val itemImage: ImageView = view.findViewById(R.id.store_image)
        //val itemName: TextView = view.findViewById(R.id.store_name)
        //val itemSubInfo: TextView = view.findViewById(R.id.store_sub_info)

        // 横スクロール画像用RecyclerView
        val recyclerViewImages: RecyclerView = view.findViewById(R.id.recycler_view_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // レイアウトをインフレートしてビューホルダーを作成
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_store_item, parent, false)


        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // データをビューにバインド
        val currentItem = itemList[position]
        holder.itemView.setOnClickListener {
            Log.d("AdapterDebug", "Item clicked: ${currentItem.name}, ID: ${currentItem.id}")
            Log.d("AdapterDebug", "Clicked on store: ${currentItem.name}")
            Log.d("AdapterDebug", "OnClickListener is triggered for store: ${currentItem.name}")

            onItemClick(currentItem) // クリックされたアイテムをリスナーに渡す
            Log.d("AdapterDebug", "Binding item: ${currentItem.name} at position: $position")
            Log.d("AdapterDebug", "OnClickListener set for store: ${currentItem.name}")

        }

        // ImageListAdapter のインスタンスを作成し、リスナーを設定
        val imageAdapter = ImageListAdapter(currentItem.images) { clickedImage ->
            Log.d("HorizontalItemAdapter", "Clicked image: ${clickedImage.name}, Store ID: ${currentItem.id}")

            // 次の画面に遷移
            val intent = Intent(holder.itemView.context, StoreProductListActivity::class.java)
            intent.putExtra("store_id", currentItem.id) // 店舗IDを渡す
            intent.putExtra("image_name", clickedImage.name) // 画像の名前を渡す（必要なら）
            holder.itemView.context.startActivity(intent)
        }





        holder.recyclerViewImages.adapter = imageAdapter
        //これ横スクロールを可能にする レイアウトマネージャー
        holder.recyclerViewImages.layoutManager = LinearLayoutManager(
            holder.recyclerViewImages.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )



        if (currentItem.isGrid) {
            // 3×3グリッド表示
            val gridLayoutManager = GridLayoutManager(
                holder.recyclerViewImages.context,
                3,
                RecyclerView.HORIZONTAL,
                false
            )
            //下のパディングの定義で20というのを、dpという単位にする定義。通常の20だけだとdpでは何から、位置調節が環境によってズレる可能性
            val paddingPx = (10 * holder.recyclerViewImages.context.resources.displayMetrics.density).toInt()
            // (パディングの追加)これはOnRouteStoreカードのグリッドの右のはみ出しのボックスのための位置調節
            holder.recyclerViewImages.setPadding( 0, 0, paddingPx +20, 0) // 左端に次のアイテムを少し見せる
            holder.recyclerViewImages.clipToPadding = false // パディング部分にアイテムを描画
            // アイテムの位置を取得（縦3行ごとに段差をつける）



            // RecyclerViewを横スクロールに対応させる
            holder.recyclerViewImages.isNestedScrollingEnabled = false // スムーズなスクロールを可能に

            holder.recyclerViewImages.layoutManager = gridLayoutManager


            // ページスクロールのスナップヘルパーを追加
            val snapHelper = CustomPagerSnapHelper(1)

            if (holder.recyclerViewImages.onFlingListener == null) {
                val snapHelper = CustomPagerSnapHelper(1)
                snapHelper.attachToRecyclerView(holder.recyclerViewImages)
                Log.d("AdapterDebug", "PagerSnapHelper attached")
            }


            // 高さを設定（グリッドに対応）
            val params = holder.recyclerViewImages.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT // 完全修飾名を使用
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            holder.recyclerViewImages.layoutParams = params

        } else {
            // 他のカードは横スクロール表示
            holder.recyclerViewImages.layoutManager =
                LinearLayoutManager(
                    holder.recyclerViewImages.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

            // 高さをデフォルトに戻す
            val params = holder.recyclerViewImages.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.recyclerViewImages.layoutParams = params
        }
        Log.d(
            "AdapterDebug",
            "isGrid: ${currentItem.isGrid}, images: ${currentItem.images.size}"
        )
        Log.d("HorizontalItemAdapter", "isGrid: ${currentItem.isGrid}")
        Log.d(
            "HorizontalItemAdapter",
            "RecyclerView LayoutManager: ${holder.recyclerViewImages.layoutManager}"
        )


        Log.d(
            "RecyclerViewState",
            "RecyclerView width: ${holder.recyclerViewImages.width}, height: ${holder.recyclerViewImages.height}"
        )
        Log.d(
            "RecyclerViewState",
            "RecyclerView child count: ${holder.recyclerViewImages.childCount}"
        )
        Log.d(
            "RecyclerViewState",
            "RecyclerView layout manager: ${holder.recyclerViewImages.layoutManager}"
        )

        Log.d(
            "AdapterDebug",
            "RecyclerView width: ${holder.recyclerViewImages.width}, height: ${holder.recyclerViewImages.height}"
        )
        Log.d("AdapterDebug", "Image size: ${currentItem.images.size}")



    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
