package jp.cordea.reiz

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.SerialDisposable
import jp.cordea.reiz.model.Record
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder
import java.util.concurrent.TimeUnit

class HomeViewModel(override val context: Context) : IViewModel, BaseObservable() {

    val adapter = HomeListAdapter(context)

    @get:Bindable
    var timeText = ""
        private set(value) {
            field = value
            notifyPropertyChanged(BR.timeText)
        }

    val onClickAdd = View.OnClickListener {
        context.startActivity(AddSessionActivity.createIntent(context))
    }

    val onClickPlay = View.OnClickListener {
        record?.let {
            if (isPlaying) {
                it.endedAt = DateTime()
                timerDisposable?.dispose()
                updateDisposable.set(RecordRepository.updateRecord(it)
                        .subscribe({
                            isPlaying = false
                        }, {
                        }))
                return@OnClickListener
            }
            it.startedAt = DateTime().also {
                startTimer(it)
            }
            updateDisposable.set(RecordRepository.updateRecord(it)
                    .subscribe({
                        isPlaying = true
                    }, {
                    }))
        }
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

    private var updateDisposable = SerialDisposable()

    init {
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
                .flatMap { Observable.fromIterable(it.menus) }
                .map { HomeListItemViewModel(it) }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.refreshItems(it)
                }, {
                    it.printStackTrace()
                })

    }

    private fun startTimer(date: DateTime) {
        val formatter = PeriodFormatterBuilder()
                .appendMinutes()
                .appendSuffix("m")
                .appendSeparator(" ")
                .appendSeconds()
                .appendSuffix("s")
                .printZeroAlways()
                .minimumPrintedDigits(2)
                .toFormatter()
        timerDisposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .map { Duration(date, DateTime()) }
                .map {
                    formatter.print(it.toPeriod())
                }
                .subscribe({
                    timeText = it
                }, {
                    it.printStackTrace()
                })
    }

    override fun dispose() {
        disposable?.dispose()
        timerDisposable?.dispose()
    }
}
