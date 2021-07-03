package com.example.pokemon.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemon.R
import com.example.pokemon.feature.home.HomeActivity


class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val TEMPO_SPLASH: Long = 2000 // Em MS

    private val mRunnable = Runnable {
        goToLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTimeDisplayActivity()
    }

    private fun goToLogin() {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()

    }

    fun setTimeDisplayActivity() {
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, TEMPO_SPLASH)
    }

    override fun onDestroy() {
        if (mDelayHandler != null){
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}