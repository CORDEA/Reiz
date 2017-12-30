package jp.cordea.reiz

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.reiz.databinding.ActivityAddMenuBinding

class AddMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMenuBinding

    private val presenter by lazy {
        AddMenuPresenter(this, binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_menu)
        setSupportActionBar(binding.toolbar)
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

        fun createIntent(context: Context): Intent =
                Intent(context, AddMenuActivity::class.java)
    }
}
