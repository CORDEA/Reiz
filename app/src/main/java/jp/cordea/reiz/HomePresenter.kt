package jp.cordea.reiz

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import jp.cordea.reiz.databinding.FragmentHomeBinding

class HomePresenter(
        override val fragment: Fragment,
        override val binding: FragmentHomeBinding
) : IFragmentPresenter<FragmentHomeBinding> {

    private val viewModel = HomeViewModel(fragment.context) {
        fragment.startActivity(it)
    }

    override fun onCreate() {
        binding.vm = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(
                fragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        binding.recyclerView.addItemDecoration(ItemDecoration(fragment.context))
    }

    override fun onResume() {
        viewModel.update()
    }

    override fun onPause() {
        viewModel.dispose()
    }
}
