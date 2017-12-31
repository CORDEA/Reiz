package jp.cordea.reiz

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import jp.cordea.reiz.databinding.FragmentHomeBinding

class HomePresenter(
        override val activity: Activity,
        override val binding: FragmentHomeBinding
) : IPresenter<FragmentHomeBinding> {

    private val viewModel = HomeViewModel(activity)

    override fun onCreate() {
        binding.vm = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
        )
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }
}