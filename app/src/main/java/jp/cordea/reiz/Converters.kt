package jp.cordea.reiz

import android.arch.persistence.room.TypeConverter
import jp.cordea.reiz.model.Menu
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @TypeConverter
    fun toTimestamp(value: Date): Long = value.time

    @TypeConverter
    fun fromMenuIds(value: String): List<Menu> =
            ReizApplication.Database.menuDao().let { dao ->
                value.split(',')
                        .map { it.toLong() }
                        .map { dao.getMenuById(it) }
                        .toList()
            }

    @TypeConverter
    fun toMenuIds(value: List<Menu>): String =
            value.map {
                it.id
            }.joinToString(",")
}