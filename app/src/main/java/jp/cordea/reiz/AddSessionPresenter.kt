package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.FragmentAddSessionBinding

class AddSessionPresenter(
        override val activity: Activity,
        override val binding: FragmentAddSessionBinding
) : IPresenter<FragmentAddSessionBinding> {

    private val viewModel = AddSessionViewModel(activity)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}
