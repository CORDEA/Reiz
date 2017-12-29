package jp.cordea.reiz.model

import android.arch.persistence.room.PrimaryKey

class Menu(
        @PrimaryKey(autoGenerate = true) val id: String,
        name: String,
        price: Int
)
