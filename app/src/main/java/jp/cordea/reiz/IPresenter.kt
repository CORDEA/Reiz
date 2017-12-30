package jp.cordea.reiz

import android.content.Context

interface IPresenter<out T> {

    val context: Context

    val binding: T

    fun onCreate()

    fun onResume()

    fun onPause()
}