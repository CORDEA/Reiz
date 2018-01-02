package jp.cordea.reiz

import android.support.v4.app.Fragment
import jp.cordea.reiz.databinding.FragmentMenuBinding

class MenuPresenter(
        override val fragment: Fragment,
        override val binding: FragmentMenuBinding
) : IFragmentPresenter<FragmentMenuBinding> {

    private val viewModel = MenuViewModel(fragment.context)

    override fun onCreate() {
        binding.vm = viewModel
    }

    override fun onResume() {
        viewModel.update()
    }

    override fun onPause() {
        viewModel.dispose()
    }
}
