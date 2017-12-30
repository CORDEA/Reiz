package jp.cordea.reiz.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import jp.cordea.reiz.model.Record

@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): List<Record>

    @Insert
    fun insertRecord(record: Record)

    @Delete
    fun deleteRecord(record: Record)
}