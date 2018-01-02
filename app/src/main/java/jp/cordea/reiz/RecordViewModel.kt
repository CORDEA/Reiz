package jp.cordea.reiz

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class RecordViewModel(
        override val context: Context
) : IViewModel {

    val adapter = RecordListAdapter(context)

    private var disposable: Disposable? = null

    override fun update() {
        disposable?.dispose()
        disposable = RecordRepository.getPastRecords()
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .map { RecordListItemViewModel(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.items = it
                }, {
                })
    }

    override fun dispose() {
        disposable?.dispose()
    }
}
