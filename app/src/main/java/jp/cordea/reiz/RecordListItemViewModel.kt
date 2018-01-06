package jp.cordea.reiz

import jp.cordea.reiz.model.Record
import org.joda.time.Duration
import org.joda.time.format.PeriodFormatterBuilder

class RecordListItemViewModel(
        record: Record
) {

    private val duration = Duration(record.startedAt, record.endedAt)

    val title: String = Format.print(duration.toPeriod())

    val price = record.selectedMenus.sumBy { it.price }

    private val totalPrice = record.menus.sumBy { it.price }

    val priceText = "¥ $price / $totalPrice"

    companion object {

        private val Format = PeriodFormatterBuilder()
                .printZeroAlways()
                .appendHours()
                .appendSuffix("h")
                .appendSeparator(" ")
                .appendMinutes()
                .appendSuffix("m")
                .toFormatter()
    }
}
