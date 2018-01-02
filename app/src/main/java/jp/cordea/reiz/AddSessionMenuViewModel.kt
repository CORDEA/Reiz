package jp.cordea.reiz

import android.content.Context
import android.widget.AdapterView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.cordea.reiz.model.Record

class AddSessionMenuViewModel(
        override val context: Context,
        private val onRequestFinish: () -> Unit
) : IViewModel, AddSessionViewModel.OnAddClickListener {

    val adapter = AddSessionMenuListAdapter(context)

    val onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
        adapter.getItem(i).incCount()
    }

    private var menuDisposable: Disposable? = null

    private var recordDisposable: Disposable? = null

    init {
        menuDisposable = MenuRepository.getMenus()
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .map { AddSessionMenuListItemViewModel(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.items = it
                }, {
                })
    }

    override fun onClick() {
        recordDisposable = Observable
                .fromIterable(adapter.items)
                .flatMap { item ->
                    Observable
                            .range(0, item.count)
                            .map { item }
                }
                .map { it.menu }
                .toList()
                .map { Record(it) }
                .flatMapCompletable {
                    RecordRepository.addRecord(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onRequestFinish()
                }, {
                    it.printStackTrace()
                })
    }

    override fun update() {
    }

    override fun dispose() {
        menuDisposable?.dispose()
        recordDisposable?.dispose()
    }
}
