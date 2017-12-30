package jp.cordea.reiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Menu(
        val name: String,
        val price: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
