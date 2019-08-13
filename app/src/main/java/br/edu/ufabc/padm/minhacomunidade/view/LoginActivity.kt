package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity:AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var userLogin: EditText
    private lateinit var userPassword: EditText
    private lateinit var auth: FirebaseAuth


    private val LOGTAG = LoginActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        init()
        listeners()
    }

    private fun init(){
        loginBtn = findViewById(R.id.login_btn)
        userLogin = findViewById(R.id.email_entry)
        userPassword = findViewById(R.id.senha_entry)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
    }

    private fun listeners(){
        loginBtn.setOnClickListener {
            doLogin()
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }


    override fun onResume() {
        super.onResume()

    }



    private fun doLogin(){
        if(userLogin.text.toString().isEmpty()){
            userLogin.error = getString(R.string.enter_email)
            userLogin.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userLogin.text.toString()).matches()){
            userLogin.error = getString(R.string.enter_valid_email)
            userLogin.requestFocus()
            return
        }
        if(userPassword.text.toString().isEmpty()){
            userPassword.error = getString(R.string.enter_password)
            userPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(userLogin.text.toString(), userPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LOGTAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(LOGTAG, "signInWithEmail:failure", task.exception)
                    updateUI(null)
                }

            }
    }


    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser!=null){
            startActivity(Intent(App.context,FeedActivity::class.java))
        }else{
            // If sign in fails, display a message to the user.
            Toast.makeText(baseContext, getString(R.string.auth_failed),
                Toast.LENGTH_SHORT).show()
        }

    }

}