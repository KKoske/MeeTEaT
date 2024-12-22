package com.websarva.wings.android.meeteat

import android.content.Intent
import android.util.Log
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
class ImageListAdapter(private val storeList: List<ImageItem>, private val onImageClick: (ImageItem) -> Unit, private val isNavigationEnabled: Boolean = true ) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    // ViewHolderクラス
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.store_image)
        val itemName: TextView = view.findViewById(R.id.store_name)
        val itemSubInfo: TextView = view.findViewById(R.id.store_sub_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // store_item_image_scroll.xmlをインフレートしてViewHolderを作成
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.store_item_image_scroll, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = storeList[position]

        // データをバインド
        holder.itemImage.setImageResource(currentImage.imageResId)
        holder.itemName.text = currentImage.name
        holder.itemSubInfo.text = currentImage.subInfo

        // 必要なら Glide や Picasso を使って画像を表示
        // Glide.with(holder.imageView.context).load(image).into(holder.imageView)
        holder.itemImage.setOnClickListener {
            Log.d("clickImageListAdapter", "LinearLayout（画像部分）がタップされました: ${currentImage.name}")
            if (isNavigationEnabled) {
                onImageClick(currentImage)
            } else {
                if (currentImage.imageResId == R.drawable.img_domino_halloween_2) {
                    val intent = Intent(holder.itemView.context, ProductDescriptionActivity::class.java)
                    intent.putExtra("product_name", currentImage.name)
                    holder.itemView.context.startActivity(intent)
                } else {
                    Log.d("ImageListAdapter", "This image is not clickable.")
                }
            }
        }



        holder.itemImage.setImageResource(currentImage.imageResId)  // 画像をセット
        holder.itemName.text = currentImage.name  // 店舗名をセット
        holder.itemSubInfo.text = currentImage.subInfo  // サブ情報をセット
        // ログを追加
        Log.d("ImageListAdapter", "Binding image: ${currentImage.name}")
        // ログを追加
        Log.d("ImageListAdapter", "Binding image at position $position: ${currentImage.name}")

        // **マージンを設定**
        val layoutParams =
            holder.itemImage.layoutParams as ViewGroup.MarginLayoutParams // ImageViewのレイアウトパラメータを取得
        val columnPosition = position % 3 // 0, 1, 2 の繰り返し（画像の段差を決める）

        when (columnPosition) {
            0 -> layoutParams.rightMargin =
                (3 * holder.itemImage.context.resources.displayMetrics.density).toInt()

            1 -> layoutParams.rightMargin =
                (5 * holder.itemImage.context.resources.displayMetrics.density).toInt()

            2 -> layoutParams.rightMargin =
                (8 * holder.itemImage.context.resources.displayMetrics.density).toInt()
        }

        holder.itemImage.layoutParams = layoutParams // 変更したマージンを適用

        // **ログを追加**
        Log.d("ImageListAdapter", "Binding image at position $position: ${currentImage.name}")
        Log.d("ImageListAdapter", "TopMargin for position $position: ${layoutParams.topMargin}")
    }

    override fun getItemCount(): Int {
        return storeList.size
    }
}

