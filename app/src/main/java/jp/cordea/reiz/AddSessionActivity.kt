package jp.cordea.reiz

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.reiz.databinding.ActivityAddSessionBinding

class AddSessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSessionBinding

    private val presenter by lazy {
        AddSessionPresenter(this, binding)
    }

    var listener: AddSessionViewModel.OnAddClickListener? = null
        set(value) {
            presenter.listener = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_session)
        setSupportActionBar(binding.toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddSessionMenuFragment.newInstance())
                .commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}
