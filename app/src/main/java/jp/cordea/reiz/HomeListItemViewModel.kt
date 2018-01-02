package jp.cordea.reiz

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import jp.cordea.reiz.model.Menu

class HomeListItemViewModel(
        menu: Menu,
        onRequestItemDeletion: (HomeListItemViewModel) -> Unit
) : BaseObservable() {

    val name = menu.name

    val priceText = menu.price.toString()

    @get:Bindable
    var isEnabled: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.enabled)
        }

    val onClick = View.OnClickListener {
        onRequestItemDeletion(this)
    }
}
