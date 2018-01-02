package jp.cordea.reiz

import android.support.v4.app.Fragment
import jp.cordea.reiz.databinding.FragmentAddSessionMenuBinding

class AddSessionMenuPresenter(
        override val fragment: Fragment,
        override val binding: FragmentAddSessionMenuBinding
) : IFragmentPresenter<FragmentAddSessionMenuBinding> {

    private val viewModel = AddSessionMenuViewModel(fragment.context) {
        fragment.activity.run {
            finish()
        }
    }

    override fun onCreate() {
        binding.vm = viewModel
        (fragment.activity as AddSessionActivity).listener = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}
