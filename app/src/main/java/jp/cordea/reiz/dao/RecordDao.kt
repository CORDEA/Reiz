package jp.cordea.reiz.dao

import android.arch.persistence.room.*
import jp.cordea.reiz.model.Record

@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): List<Record>

    @Query("SELECT * FROM record WHERE ended_at < 0 LIMIT 1")
    fun getCurrentRecord(): Record?

    @Update
    fun updateRecord(record: Record)

    @Insert
    fun insertRecord(record: Record)

    @Delete
    fun deleteRecord(record: Record)
}