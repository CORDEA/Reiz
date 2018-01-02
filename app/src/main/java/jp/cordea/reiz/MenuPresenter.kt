package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.FragmentMenuBinding

class MenuPresenter(
        override val activity: Activity,
        override val binding: FragmentMenuBinding
) : IPresenter<FragmentMenuBinding> {

    private val viewModel = MenuViewModel(activity)

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
