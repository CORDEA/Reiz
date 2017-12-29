package jp.cordea.reiz

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                transaction.replace(R.id.container, HomeFragment.newInstance())
            }
            R.id.navigation_dashboard -> {
                transaction.replace(R.id.container, RecordFragment.newInstance())
            }
            R.id.navigation_notifications -> {
                transaction.replace(R.id.container, MenuFragment.newInstance())
            }
        }
        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit()
    }
}
