package jp.cordea.reiz

import android.app.Activity

interface IPresenter<out T> {

    val activity: Activity

    val binding: T

    fun onCreate()

    fun onResume()

    fun onPause()
}