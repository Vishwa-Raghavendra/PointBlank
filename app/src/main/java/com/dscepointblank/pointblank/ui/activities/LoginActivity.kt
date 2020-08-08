package com.dscepointblank.pointblank.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.ui.fragments.Login.LoginFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction().replace(
            R.id.container, LoginFragment.newInstance()
        ).commit()
    }
}