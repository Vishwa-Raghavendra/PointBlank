package com.dscepointblank.pointblank.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.utilityClasses.Constants
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_splash.view.*
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var timeInMillis:Long = 3000
        val nextIntent = Intent(this, HomeActivity::class.java)

        val text = "<font color=#00C853>Point</font> <font color=#fafafa>Blank</font>"
        activity_splashPbTv.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val random = Random.nextInt(Constants.tagLineSize)
        activity_splashTagLineTV.text = Constants.tagLines[random]

        val intent = intent!!
        val action = intent.action!!

        if (Intent.ACTION_VIEW == action) {

            timeInMillis = 500
            val mode = if (intent.dataString!!.contains("forum.dsce"))
                Constants.LINK_FORUM_DSCE
            else
                Constants.LINK_WRITEUP_DSCE

            nextIntent.putExtra(Constants.IS_VIEW, mode)
            nextIntent.putExtra(Constants.LINK_VIEW, intent.dataString!!)
        } else {
            nextIntent.putExtra(Constants.IS_VIEW, 0)
        }

        nextActivity(nextIntent,timeInMillis)
    }

    private fun nextActivity(intent: Intent,timeInMills:Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }, timeInMills)
    }
}