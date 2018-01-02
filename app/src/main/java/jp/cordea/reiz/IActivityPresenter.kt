package jp.cordea.reiz

import android.app.Activity

interface IActivityPresenter<out T> : IPresenter<T> {

    val activity: Activity

}