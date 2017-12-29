package jp.cordea.reiz

import android.app.Application
import android.arch.persistence.room.Room

class ReizApplication : Application() {

    companion object {
        lateinit var Database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        Database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "reiz"
        ).build()
    }
}