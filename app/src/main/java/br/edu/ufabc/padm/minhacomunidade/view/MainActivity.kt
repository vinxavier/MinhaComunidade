package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.edu.ufabc.padm.minhacomunidade.view.Cadastro.CadastroActvity
import br.edu.ufabc.padm.minhacomunidade.R


class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var cadastroBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        loginBtn = findViewById(R.id.login_btn)
        cadastroBtn = findViewById(R.id.cadastrar_btn)
    }

    override fun onResume() {
        super.onResume()

        loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        cadastroBtn.setOnClickListener{
            startActivity(Intent(this, CadastroActvity::class.java))
        }

    }
}
