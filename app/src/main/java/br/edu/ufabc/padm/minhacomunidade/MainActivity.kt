package br.edu.ufabc.padm.minhacomunidade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        loginBtn = findViewById(R.id.login_btn)
    }

    override fun onResume() {
        super.onResume()

        loginBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}
