package com.websarva.wings.android.meeteat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemAdapter(private val itemList: List<StoreItem>) : RecyclerView.Adapter<HorizontalItemAdapter.ItemViewHolder>() {

    // ビューホルダークラス
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.heathy)
        val itemName: TextView = view.findViewById(R.id.store_name)
        val itemSubInfo: TextView = view.findViewById(R.id.store_sub_info)
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
        holder.itemName.text = currentItem.name
        holder.itemSubInfo.text = currentItem.subInfo
        holder.itemImage.setImageResource(currentItem.imageResId) // リソースIDから画像を設定

        // ImageListAdapter を設定
        val imageList = currentItem.images  // 各店舗に関連する画像のリスト
        val imageAdapter = ImageListAdapter(imageList)
        holder.recyclerViewImages.adapter = imageAdapter
        holder.recyclerViewImages.layoutManager = LinearLayoutManager(holder.recyclerViewImages.context, LinearLayoutManager.HORIZONTAL, false)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}
