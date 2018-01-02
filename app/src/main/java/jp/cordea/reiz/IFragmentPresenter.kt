package jp.cordea.reiz

import android.support.v4.app.Fragment

interface IFragmentPresenter<out T> : IPresenter<T> {

    val fragment: Fragment

}