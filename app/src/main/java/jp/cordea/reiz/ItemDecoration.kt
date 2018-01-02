package jp.cordea.reiz

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        view?.let {
            parent?.let { parent ->
                val lp = it.layoutParams as RecyclerView.LayoutParams

                if (parent.getChildAdapterPosition(it) == 0) {
                    val margin = context.resources.getDimensionPixelSize(
                            R.dimen.list_item_home_horizontal_margin)
                    lp.leftMargin = margin
                } else {
                    lp.leftMargin = 0
                }
            }
        }
    }
}