package jp.cordea.reiz

import android.app.Activity
import jp.cordea.reiz.databinding.FragmentHomeBinding

class HomePresenter(
        override val activity: Activity,
        override val binding: FragmentHomeBinding
) : IPresenter<FragmentHomeBinding> {

    private val viewModel = HomeViewModel(activity)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}