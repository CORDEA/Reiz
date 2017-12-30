package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class HomeViewModel(override val context: Context) : IViewModel, BaseObservable() {

    val adapter = HomeListAdapter(context)

    @Bindable
    var isInProgress = false
        private set(value) {
            field = value
            notifyPropertyChanged(BR.isInProgress)
        }

    private var disposable: Disposable? = null

    init {
        disposable = Single
                .fromCallable {
                    Optional.ofNullable(
                            ReizApplication.Database.recordDao().getCurrentRecord())
                }
                .subscribeOn(Schedulers.io())
                .doOnSuccess {
                    isInProgress = it.isPresent
                }
                .filter { it.isPresent }
                .map { it.get() }
                .toObservable()
                .flatMap { Observable.fromIterable(it.menus) }
                .map { HomeListItemViewModel(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.refreshItems(it)
                }, {

                })

    }

    override fun dispose() {
        disposable?.dispose()
    }
}