package hu.stewe93.arcoresamples

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import hu.stewe93.arcoresamples.ui.main.MainFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentFrame, MainFragment(), "AR")
                .addToBackStack("AR")
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
