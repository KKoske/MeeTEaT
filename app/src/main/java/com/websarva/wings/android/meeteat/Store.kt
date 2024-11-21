package com.websarva.wings.android.meeteat

import android.content.Context
import android.util.Log

data class Store(
    val id: Int,
    val name: String,
    val address: String,
    val image_url: String, // フィールド名を統一
    val images: List<ImageItem>, // 複数の画像リスト
) {
    // 画像ファイル名からリソースIDを取得するメソッド
    fun getImageResId(context: Context): Int {
        val resId = context.resources.getIdentifier(image_url, "drawable", context.packageName)
        if (resId == 0) {
            Log.e("Store", "Invalid image resource: $image_url")
        }
        return resId
    }

}
