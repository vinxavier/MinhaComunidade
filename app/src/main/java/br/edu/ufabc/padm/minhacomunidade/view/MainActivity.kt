package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.view.Cadastro.CadastroActvity
import br.edu.ufabc.padm.minhacomunidade.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var cadastroBtn: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        updateUI(currentUser)
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

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser!=null){
            startActivity(Intent(App.context,FeedActivity::class.java))
            finish()
        }
    }
}
