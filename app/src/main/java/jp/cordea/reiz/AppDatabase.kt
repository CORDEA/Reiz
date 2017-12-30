package jp.cordea.reiz

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import jp.cordea.reiz.dao.MenuDao
import jp.cordea.reiz.dao.RecordDao
import jp.cordea.reiz.model.Menu
import jp.cordea.reiz.model.Record

@Database(entities = [Menu::class, Record::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    abstract fun recordDao(): RecordDao
}
