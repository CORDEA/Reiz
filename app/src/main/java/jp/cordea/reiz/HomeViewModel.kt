package jp.cordea.reiz

import android.content.Context

class HomeViewModel(override val context: Context) : IViewModel {

    val adapter = HomeListAdapter(context)

    override fun dispose() {
    }
}