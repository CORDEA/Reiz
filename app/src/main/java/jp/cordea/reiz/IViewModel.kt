package jp.cordea.reiz

import android.content.Context

interface IViewModel {

    val context: Context

    fun dispose()
}