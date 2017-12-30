package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.ActivityAddMenuBinding

class AddMenuPresenter(
        override val activity: Activity,
        override val binding: ActivityAddMenuBinding
) : IPresenter<ActivityAddMenuBinding> {

    private val viewModel = AddMenuViewModel(activity) {
        activity.finish()
    }

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}