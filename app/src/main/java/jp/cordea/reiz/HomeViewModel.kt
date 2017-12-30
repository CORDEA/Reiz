package jp.cordea.reiz

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.*

class HomeViewModel(override val context: Context) : IViewModel {

    val adapter = HomeListAdapter(context)

    private var disposable: Disposable? = null

    init {
        disposable = Single
                .fromCallable {
                    Optional.ofNullable(
                            ReizApplication.Database.recordDao().getCurrentRecord())
                }
                .filter { it.isPresent }
                .map { it.get() }
                .toObservable()
                .flatMap { Observable.fromIterable(it.menus) }
                .map { HomeListItemViewModel(it) }
                .toList()
                .subscribe({
                    adapter.refreshItems(it)
                }, {

                })

    }

    override fun dispose() {
        disposable?.dispose()
    }
}