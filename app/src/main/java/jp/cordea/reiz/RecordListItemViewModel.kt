package jp.cordea.reiz

import jp.cordea.reiz.model.Record
import org.joda.time.format.DateTimeFormat

class RecordListItemViewModel(
        record: Record
) {

    val title = "${Format.print(record.startedAt)} ~ ${Format.print(record.endedAt)}"

    private val price = record.selectedMenus.sumBy { it.price }

    private val totalPrice = record.menus.sumBy { it.price }

    val priceText = "¥ $price / $totalPrice"

    companion object {

        private val Format = DateTimeFormat.forPattern("HH:mm:ss")
    }
}
