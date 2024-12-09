package com.websarva.wings.android.meeteat

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView


class CustomPagerSnapHelper(private val spanCount: Int) : LinearSnapHelper() {
    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        val distances = super.calculateDistanceToFinalSnap(layoutManager, targetView)
        distances?.let {
            if (layoutManager is GridLayoutManager && layoutManager.orientation == RecyclerView.HORIZONTAL) {
                it[0] *= spanCount // 横3マス分の距離を考慮
                Log.d("SnapHelper", "Distance to final snap: x=${distances?.get(0)}, y=${distances?.get(1)}")

            }
        }
        return distances
    }
}

