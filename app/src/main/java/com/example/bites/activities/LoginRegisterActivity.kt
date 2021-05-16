package com.example.bites.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.bites.R
import com.example.bites.fragments.LoginFragment


class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        val sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE)


        //If already logged in once, directly open Dashboard
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        } else {
            openLoginFragment()
        }
    }

    fun openLoginFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayout,
            LoginFragment(this)
        ).commit()

        supportActionBar?.title = "DashBoard"
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.frameLayout)) {
            !is LoginFragment -> openLoginFragment()
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                openLoginFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}