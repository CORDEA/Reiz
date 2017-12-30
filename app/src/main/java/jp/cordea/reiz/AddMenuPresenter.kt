package jp.cordea.reiz

import android.content.Context
import jp.cordea.reiz.databinding.ActivityAddMenuBinding

class AddMenuPresenter(
        override val context: Context,
        override val binding: ActivityAddMenuBinding
) : IPresenter<ActivityAddMenuBinding> {

    private val viewModel = AddMenuViewModel(context)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}