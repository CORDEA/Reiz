package jp.cordea.reiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.reiz.databinding.FragmentAddSessionMenuBinding

class AddSessionMenuFragment : Fragment() {

    private lateinit var binding: FragmentAddSessionMenuBinding

    private val presenter by lazy {
        AddSessionMenuPresenter(activity, binding)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View =
            FragmentAddSessionMenuBinding.inflate(inflater, container, false).also {
                binding = it
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    companion object {

        fun newInstance(): AddSessionMenuFragment =
                AddSessionMenuFragment()
    }
}
