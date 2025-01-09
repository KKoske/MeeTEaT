package com.websarva.wings.android.meeteat

import android.content.Context
import android.util.Log

data class Product(
    val id: Int,
    val storeId: Int,
    val name: String,
    val image_url: String,
    val price: Double,
    val description: String,
    val preparationTime: Int
) {
    // 画像URLをリソースIDに変換
    fun getImageResId(context: Context): Int {
        val resId = context.resources.getIdentifier(image_url, "drawable", context.packageName)
        if (resId == 0) {
            Log.e("Product", "Invalid image resource: $image_url")
        }
        return resId
    }
}
