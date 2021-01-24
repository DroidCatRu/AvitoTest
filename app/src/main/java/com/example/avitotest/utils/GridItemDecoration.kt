package com.example.avitotest.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridItemDecoration(gridSpacingPx: Int, gridSize: Int) : RecyclerView.ItemDecoration() {

  private var mSizeGridSpacingPx: Int = gridSpacingPx
  private var mGridSize: Int = gridSize

  override fun getItemOffsets(
      outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    val position = parent.getChildAdapterPosition(view)
    val column: Int = position % mGridSize

    outRect.left = mSizeGridSpacingPx - column * mSizeGridSpacingPx / mGridSize
    outRect.right = (column + 1) * mSizeGridSpacingPx / mGridSize
    if (position < mGridSize) {
      outRect.top = mSizeGridSpacingPx
    }
    outRect.bottom = mSizeGridSpacingPx
  }
}