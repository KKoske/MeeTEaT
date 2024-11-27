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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_store_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // データをビューにバインド
        val currentItem = itemList[position]
        Log.d("AdapterDebug", "Binding item: ${currentItem.name} at position: $position")

        Log.d("AdapterDebug", "RecyclerView width: ${holder.recyclerViewImages.width}, height: ${holder.recyclerViewImages.height}")
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
        holder.recyclerViewImages.layoutManager = LinearLayoutManager(holder.recyclerViewImages.context, LinearLayoutManager.HORIZONTAL, false)



        if (currentItem.isGrid) {
            // 3×3グリッド表示
            val gridLayoutManager = GridLayoutManager(holder.recyclerViewImages.context, 3,LinearLayoutManager.HORIZONTAL, false)

            // RecyclerViewを横スクロールに対応させる
            holder.recyclerViewImages.isNestedScrollingEnabled = false // スムーズなスクロールを可能に

            holder.recyclerViewImages.layoutManager = gridLayoutManager
            holder.recyclerViewImages.adapter = ImageListAdapter(currentItem.images)

            // ページスクロールのスナップヘルパーを追加
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(holder.recyclerViewImages)

            // 高さを設定（グリッドに対応）
            val params = holder.recyclerViewImages.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT // 完全修飾名を使用
            holder.recyclerViewImages.layoutParams = params
        } else {
            // 他のカードは横スクロール表示
            holder.recyclerViewImages.layoutManager =
                LinearLayoutManager(holder.recyclerViewImages.context, LinearLayoutManager.HORIZONTAL, false)
            holder.recyclerViewImages.adapter = ImageListAdapter(currentItem.images)

            // 高さをデフォルトに戻す
            val params = holder.recyclerViewImages.layoutParams
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.recyclerViewImages.layoutParams = params
        }
        Log.d("AdapterDebug", "isGrid: ${currentItem.isGrid}, images: ${currentItem.images.size}")
        Log.d("HorizontalItemAdapter", "isGrid: ${currentItem.isGrid}")
        Log.d("HorizontalItemAdapter", "RecyclerView LayoutManager: ${holder.recyclerViewImages.layoutManager}")



    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
