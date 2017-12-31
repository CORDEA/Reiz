package jp.cordea.reiz

import android.content.Context
import android.view.View

class AddSessionViewModel(override val context: Context) : IViewModel {

    interface OnAddClickListener {
        fun onClick()
    }

    var listener: OnAddClickListener? = null

    val onClick = View.OnClickListener {
        listener?.onClick()
    }

    override fun dispose() {
    }
}