package jp.cordea.reiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.reiz.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val presenter by lazy {
        HomePresenter(this, binding)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View =
            FragmentHomeBinding.inflate(inflater, container, false).also {
                binding = it
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
