package jp.cordea.reiz

import android.databinding.BaseObservable
import android.databinding.Bindable
import jp.cordea.reiz.model.Menu

class AddSessionListItemViewModel(menu: Menu) : BaseObservable() {

    val name = menu.name

    val priceText = menu.price.toString()

    @Bindable
    var count = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.count)
        }

    fun incCount() {
        count = count.inc()
    }
}