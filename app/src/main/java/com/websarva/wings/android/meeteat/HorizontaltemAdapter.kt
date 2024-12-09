package com.websarva.wings.android.meeteat

import android.content.Context
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



class HorizontalItemAdapter(private val itemList: List<Store>) : RecyclerView.Adapter<HorizontalItemAdapter.ItemViewHolder>() {
    init {
        Log.d("HorizontalItemAdapter", "Adapter initialized")
    }


    // ビューホルダークラス
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        Log.d("AdapterDebug", "Binding item: ${currentItem.name} at position: $position")

        Log.d(
            "AdapterDebug",
            "RecyclerView width: ${holder.recyclerViewImages.width}, height: ${holder.recyclerViewImages.height}"
        )
        Log.d("AdapterDebug", "Image size: ${currentItem.images.size}")



        // ImageListAdapter を設定
        // 店舗に関連付けられた画package com.websarva.wings.android.meeteat
        //
        //import android.content.Context
        //import android.util.Log
        //import android.view.LayoutInflater
        //import android.view.View
        //import android.view.ViewGroup
        //import android.widget.ImageView
        //import android.widget.TextView
        //import androidx.recyclerview.widget.LinearLayoutManager
        //import androidx.recyclerview.widget.RecyclerView
        //
        //
        //
        //class HorizontalItemAdapter(private val itemList: List<Store>) : RecyclerView.Adapter<HorizontalItemAdapter.ItemViewHolder>() {
        //
        //    // ビューホルダークラス
        //    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        //val itemImage: ImageView = view.findViewById(R.id.store_image)
        //        //val itemName: TextView = view.findViewById(R.id.store_name)
        //        //val itemSubInfo: TextView = view.findViewById(R.id.store_sub_info)
        //        // 横スクロール画像用RecyclerView
        //        val recyclerViewImages: RecyclerView = view.findViewById(R.id.recycler_view_images)
        //    }
        //
        //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //        // レイアウトをインフレートしてビューホルダーを作成
        //        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_store_item, parent, false)
        //        return ItemViewHolder(view)
        //    }
        //
        //    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //        // データをビューにバインド
        //        val currentItem = itemList[position]
        //        //ログ用コード
        //        if (currentItem.images.isEmpty()) {
        //            Log.w("HorizontalItemAdapter", "画像リストが空です: ${currentItem.name}")
        //            holder.recyclerViewImages.visibility = View.GONE // 空の場合非表示
        //            return
        //        }
        //
        //
        //        // ImageListAdapter を設定
        //        // 店舗に関連付けられた画像リストを渡す
        //        val imageAdapter = ImageListAdapter(currentItem.images)
        //        holder.recyclerViewImages.adapter = imageAdapter
        //        //これ横スクロールを可能にする レイアウトマネージャー
        //        holder.recyclerViewImages.layoutManager = LinearLayoutManager(holder.recyclerViewImages.context, LinearLayoutManager.HORIZONTAL, false)
        //
        //    }
        //
        //    override fun getItemCount(): Int {
        //        return itemList.size
        //    }
        //}像リストを渡す
        val imageAdapter = ImageListAdapter(currentItem.images)
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
            holder.recyclerViewImages.adapter = ImageListAdapter(currentItem.images)

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


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}