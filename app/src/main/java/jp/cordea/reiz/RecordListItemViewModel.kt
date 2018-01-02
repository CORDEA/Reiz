package jp.cordea.reiz

import jp.cordea.reiz.model.Record
import org.joda.time.format.DateTimeFormat

class RecordListItemViewModel(
        record: Record
) {

    val title = "${Format.print(record.startedAt)} ~ ${Format.print(record.endedAt)}"

    val priceText = "¥ ${record.menus.sumBy { it.price }}"

    companion object {

        private val Format = DateTimeFormat.forPattern("HH:mm:ss")
    }
}
