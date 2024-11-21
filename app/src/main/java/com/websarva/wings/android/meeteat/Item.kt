package com.websarva.wings.android.meeteat

data class Item(
    val id: Int,               // 商品のID
    val storeId: String,       // 商品が属する店舗のID
    val name: String,          // 商品名
    val imageUrl: String,      // 商品の画像
    val price: Double,         // 商品の価格
    val description: String,   // 商品の説明
    val preparationTime: Int   // 提供時間（分）
)