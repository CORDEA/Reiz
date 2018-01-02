package jp.cordea.reiz

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import jp.cordea.reiz.databinding.FragmentHomeBinding

class HomePresenter(
        override val fragment: Fragment,
        override val binding: FragmentHomeBinding
) : IFragmentPresenter<FragmentHomeBinding> {

    private val viewModel = HomeViewModel(fragment.context) {
        fragment.startActivityForResult(it, RequestCode)
    }

    override fun onCreate() {
        binding.vm = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(
                fragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
        )
    }

    override fun onResume() {
    }

    override fun onPause() {
        viewModel.dispose()
    }

    override fun onUpdate() {
        viewModel.update()
    }

    companion object {

        const val RequestCode = 100
    }
}