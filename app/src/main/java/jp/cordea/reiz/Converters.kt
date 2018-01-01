package jp.cordea.reiz

import android.arch.persistence.room.TypeConverter
import jp.cordea.reiz.model.Menu
import org.joda.time.DateTime

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): DateTime? =
            if (value > 0L) {
                DateTime(value)
            } else {
                null
            }

    @TypeConverter
    fun toTimestamp(value: DateTime?): Long = value?.millis ?: -1L

    @TypeConverter
    fun fromMenuIds(value: String): List<Menu> =
            ReizApplication.Database.menuDao().let { dao ->
                value.split(',')
                        .map { it.toLong() }
                        .toLongArray()
                        .let {
                            val ids = dao.getMenuByIds(it)
                                    .associate { it.id to it }
                            it.map {
                                ids.getValue(it)
                            }.toList()
                        }
            }

    @TypeConverter
    fun toMenuIds(value: List<Menu>): String =
            value.map {
                it.id
            }.joinToString(",")
}