package jp.cordea.reiz

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.reiz.databinding.FragmentAddSessionBinding

class AddSessionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View =
            FragmentAddSessionBinding.inflate(inflater, container, false).root

    companion object {

        fun newInstance(): AddSessionFragment =
                AddSessionFragment()
    }
}
