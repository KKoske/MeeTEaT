package com.websarva.wings.android.meeteat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// データモデル
data class ImageItem(
    val imageResId: Int,  // 画像のリソースID
    val name: String,     // 店舗名などのテキスト
    val subInfo: String   // サブ情報（例: 受け取り可能などの情報）
)

// アダプタークラス
class ImageListAdapter(private val imageList: List<ImageItem>) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    // ViewHolderクラス
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.heathy)
        val itemName: TextView = view.findViewById(R.id.store_name)
        val itemSubInfo: TextView = view.findViewById(R.id.store_sub_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // store_item_image_scroll.xmlをインフレートしてViewHolderを作成
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item_image_scroll, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // データをビューにバインド
        val currentItem = imageList[position]
        holder.itemName.text = currentItem.name
        holder.itemSubInfo.text = currentItem.subInfo
        holder.itemImage.setImageResource(currentItem.imageResId)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
