package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.ActivityAddSessionBinding

class AddSessionPresenter(
        override val activity: Activity,
        override val binding: ActivityAddSessionBinding
) : IPresenter<ActivityAddSessionBinding> {

    var listener: AddSessionViewModel.OnAddClickListener? = null
        set(value) {
            viewModel.listener = value
        }

    private val viewModel = AddSessionViewModel(activity)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }

    override fun onUpdate() {
    }
}