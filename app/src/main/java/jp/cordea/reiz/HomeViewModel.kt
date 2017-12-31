package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class HomeViewModel(override val context: Context) : IViewModel, BaseObservable() {

    val adapter = HomeListAdapter(context)

    val onClickAdd = View.OnClickListener {
        context.startActivity(AddSessionActivity.createIntent(context))
    }

    val onClickPlay = View.OnClickListener {
    }

    @get:Bindable
    var isInProgress = false
        private set(value) {
            field = value
            notifyPropertyChanged(BR.inProgress)
        }

    private var disposable: Disposable? = null

    init {
        disposable = RecordRepository.getCurrentRecord()
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