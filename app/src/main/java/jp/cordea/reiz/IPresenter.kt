package jp.cordea.reiz

interface IPresenter<out T> {

    val binding: T

    fun onCreate()

    fun onResume()

    fun onPause()
}
