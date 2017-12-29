package jp.cordea.reiz

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import jp.cordea.reiz.dao.MenuDao
import jp.cordea.reiz.model.Menu

@Database(entities = [Menu::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao
}
