package jp.cordea.reiz

import android.support.v4.app.Fragment
import jp.cordea.reiz.databinding.FragmentRecordBinding

class RecordPresenter(
        override val fragment: Fragment,
        override val binding: FragmentRecordBinding
) : IFragmentPresenter<FragmentRecordBinding> {

    private val viewModel = RecordViewModel(fragment.context)

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
