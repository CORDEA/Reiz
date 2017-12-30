package jp.cordea.reiz

import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class MenuViewModel(override val context: Context) : IViewModel {

    val adapter = MenuListAdapter(context)

    val onClick = View.OnClickListener {
        context.startActivity(AddMenuActivity.createIntent(context))
    }

    init {
        MenuRepository.getMenus()
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMap {
                    Observable.fromIterable(it)
                }
                .map {
                    MenuListItemViewModel(it)
                }
                .toList()
                .subscribe({
                    adapter.setItems(it)
                }, {
                })
    }

    override fun dispose() {
    }
}