package jp.cordea.reiz.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity
data class Record(
        @ColumnInfo(name = "menus")
        val menus: List<Menu>,
        @ColumnInfo(name = "started_at")
        var startedAt: DateTime? = null,
        @ColumnInfo(name = "ended_at")
        var endedAt: DateTime? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "selected_menus")
    var selectedMenus: List<Menu> = emptyList()
}
