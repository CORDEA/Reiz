package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View

class AddMenuViewModel(override val context: Context) : IViewModel, BaseObservable() {

    @Bindable
    var name = ""

    @Bindable
    var price = ""

    val onClick = View.OnClickListener {
        val price = price.toIntOrNull() ?: return@OnClickListener
        if (name.isBlank()) {
            return@OnClickListener
        }
    }

    override fun dispose() {
    }
}