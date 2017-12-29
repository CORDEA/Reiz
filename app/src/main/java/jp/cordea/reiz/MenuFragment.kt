package jp.cordea.reiz

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle
    ): View {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    companion object {

        fun newInstance(): MenuFragment {
            return MenuFragment()
        }
    }
}
