package jp.cordea.reiz.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import jp.cordea.reiz.model.Menu

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu")
    fun getMenus(): List<Menu>

    @Query("SELECT * FROM menu WHERE id = :id")
    fun getMenuById(id: Long): Menu

    @Insert
    fun insertMenu(menu: Menu)

    @Delete
    fun deleteMenu(menu: Menu)
}
