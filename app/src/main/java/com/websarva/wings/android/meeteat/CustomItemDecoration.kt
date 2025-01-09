package com.websarva.wings.android.meeteat


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val layoutManager = parent.layoutManager as? RecyclerView.LayoutManager
        val column = position % 3 // グリッドの列数（3列の場合）

        // 左右のスペーシング
        outRect.left = if (column == 0) 0 else spacing / 2
        outRect.right = if (column == 2) spacing / 2 else spacing / 2

        // 上下のスペーシング
        outRect.top = spacing / 2
        outRect.bottom = spacing / 2
        // デバッグ用のログを追加
        android.util.Log.d("CustomItemDecoration", "Position: $position, Column: $column, Spacing: $spacing, OutRect: $outRect")
    }
}
