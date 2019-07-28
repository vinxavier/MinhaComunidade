package br.edu.ufabc.padm.minhacomunidade

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity() {

    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        init()
    }

    private fun init(){
        loginBtn = findViewById(R.id.login_btn)
    }


    override fun onResume() {
        super.onResume()

        loginBtn.setOnClickListener {
            startActivity(Intent(this,FeedActivity::class.java))
        }

    }
}