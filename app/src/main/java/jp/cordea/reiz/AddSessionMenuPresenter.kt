package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.FragmentAddSessionMenuBinding

class AddSessionMenuPresenter(
        override val activity: Activity,
        override val binding: FragmentAddSessionMenuBinding
) : IPresenter<FragmentAddSessionMenuBinding> {

    private val viewModel = AddSessionMenuViewModel(activity)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}
