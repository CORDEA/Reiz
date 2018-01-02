package jp.cordea.reiz

import android.content.Context
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MenuViewModel(override val context: Context) : IViewModel {

    val adapter = MenuListAdapter(context)

    val onClick = View.OnClickListener {
        context.startActivity(AddMenuActivity.createIntent(context))
    }

    private var disposable: Disposable? = null

    override fun update() {
        disposable?.dispose()
        disposable = MenuRepository.getMenus()
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
                    adapter.items = it
                }, {
                })
    }

    override fun dispose() {
        disposable?.dispose()
    }
}
