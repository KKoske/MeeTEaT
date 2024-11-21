package com.websarva.wings.android.meeteat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class HorizontalItemAdapter(private val itemList: List<Store>) : RecyclerView.Adapter<HorizontalItemAdapter.ItemViewHolder>() {

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
        if (currentItem.images.isEmpty()) {
            Log.w("HorizontalItemAdapter", "画像リストが空です: ${currentItem.name}")
            holder.recyclerViewImages.visibility = View.GONE // 空の場合非表示
            return
        }


        // ImageListAdapter を設定
        // 店舗に関連付けられた画像リストを渡す
        val imageAdapter = ImageListAdapter(currentItem.images)
        holder.recyclerViewImages.adapter = imageAdapter
        holder.recyclerViewImages.layoutManager = LinearLayoutManager(holder.recyclerViewImages.context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
