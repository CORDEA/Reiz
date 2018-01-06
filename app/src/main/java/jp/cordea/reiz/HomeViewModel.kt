package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.cordea.reiz.model.Record
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import java.util.concurrent.TimeUnit

class HomeViewModel(
        override val context: Context
) : IViewModel, BaseObservable() {

    val adapter = HomeListAdapter(context)

    @get:Bindable
    var timeText = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.timeText)
        }

    @get:Bindable
    val priceText: String
        get() = price?.toString() ?: ""

    private var price: Int? = null
        private set(value) {
            field = value
            notifyPropertyChanged(BR.priceText)
        }

    val onClickAdd = View.OnClickListener {
        context.startActivity(AddSessionActivity.createIntent(context))
    }

    val onClickPlay = View.OnClickListener {
        switchPlayingState()
    }

    @get:Bindable
    var isInProgress = false
        private set(value) {
            field = value
            notifyPropertyChanged(BR.inProgress)
        }

    @get:Bindable
    var isPlaying = false
        private set(value) {
            field = value
            notifyPropertyChanged(BR.playing)
        }

    private var record: Record? = null

    private var disposable: Disposable? = null

    private var timerDisposable: Disposable? = null

    private var updateDisposable: Disposable? = null

    private var itemDisposable: Disposable? = null

    private fun init() {
        disposable?.dispose()
        disposable = RecordRepository.getCurrentRecord()
                .doOnSuccess {
                    isInProgress = it.isPresent
                }
                .filter { it.isPresent }
                .map { it.get() }
                .toObservable()
                .doOnNext {
                    record = it
                }
                .doOnNext {
                    isPlaying = it.startedAt != null
                    if (isPlaying) {
                        startTimer(it.startedAt!!)
                    }
                }
                .map {
                    var menus = it.menus
                    it.selectedMenus.forEach {
                        menus = menus.minus(it)
                    }
                    menus
                }
                .flatMap {
                    Observable.fromIterable(it)
                }
                .map {
                    HomeListItemViewModel(it) {
                        selectItem(it)
                    }
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.refreshItems(it)
                    if (isPlaying) {
                        adapter.isEnableItems = true
                    }
                }, {
                    it.printStackTrace()
                })
    }

    private fun switchPlayingState() {
        record?.let {
            updateDisposable?.dispose()
            if (isPlaying) {
                it.endedAt = DateTime()
                timerDisposable?.dispose()
                updateDisposable = RecordRepository.updateRecord(it)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isPlaying = false
                            isInProgress = false
                            timeText = ""
                            price = null
                            adapter.isEnableItems = false
                            adapter.refreshItems(emptyList())
                        }, {
                        })
                return
            }
            it.startedAt = DateTime().also {
                startTimer(it)
            }
            updateDisposable = RecordRepository.updateRecord(it)
                    .subscribe({
                        isPlaying = true
                        adapter.isEnableItems = true
                    }, {
                    })
        }
    }

    private fun selectItem(model: HomeListItemViewModel) {
        val record = record ?: return
        val menus = record.selectedMenus.toMutableList().apply {
            add(model.menu)
        }
        record.selectedMenus = menus

        itemDisposable?.dispose()
        itemDisposable = RecordRepository.updateRecord(record)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.removeItem(model)
                    price = (price ?: 0).plus(model.menu.price)
                }, {
                })
    }

    private fun startTimer(date: DateTime) {

        timerDisposable?.dispose()
        timerDisposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .map { Duration(date, DateTime()) }
                .map {
                    Format.print(it.toPeriod())
                }
                .subscribe({
                    timeText = it
                }, {
                    it.printStackTrace()
                })
    }

    override fun update() {
        init()
    }

    override fun dispose() {
        disposable?.dispose()
        timerDisposable?.dispose()
        updateDisposable?.dispose()
        itemDisposable?.dispose()
    }

    companion object {

        private val Format = PeriodFormatterBuilder()
                .printZeroAlways()
                .appendHours()
                .appendSuffix("h")
                .appendSeparator(" ")
                .appendMinutes()
                .appendSuffix("m")
                .appendSeparator(" ")
                .appendSeconds()
                .appendSuffix("s")
                .toFormatter()
    }
}
