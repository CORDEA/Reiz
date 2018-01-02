package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.cordea.reiz.model.Menu
import java.util.*

class AddMenuViewModel(
        override val context: Context,
        private val onRequestFinish: () -> Unit
) : IViewModel, BaseObservable() {

    @Bindable
    var name = ""

    @Bindable
    var price = ""

    val onClick = View.OnClickListener {
        disposable = Single.just(name to price)
                .subscribeOn(Schedulers.io())
                .map {
                    it.first to Optional.ofNullable(it.second.toIntOrNull())
                }
                .filter { it.first.isNotBlank() }
                .filter { it.second.isPresent }
                .map { it.first to it.second.get() }
                .map { Menu(it.first, it.second) }
                .flatMapCompletable {
                    MenuRepository.addMenu(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onRequestFinish()
                }, {
                })
    }

    private var disposable: Disposable? = null

    override fun update() {
    }

    override fun dispose() {
        disposable?.dispose()
    }
}