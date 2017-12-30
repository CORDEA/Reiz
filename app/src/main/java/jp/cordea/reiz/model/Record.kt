package jp.cordea.reiz.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Record(
        @ColumnInfo(name = "started_at")
        val startedAt: Date,
        @ColumnInfo(name = "ended_at")
        val endedAt: Date,
        @ColumnInfo(name = "menus")
        val menus: List<Menu>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
