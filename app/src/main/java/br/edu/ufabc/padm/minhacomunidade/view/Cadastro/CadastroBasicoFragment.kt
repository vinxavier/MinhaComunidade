package br.edu.ufabc.padm.minhacomunidade.view.Cadastro


import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroBasicoFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class CadastroBasicoFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userLogin: EditText
    private lateinit var userPassword: EditText


    private val LOGTAG = CadastroBasicoFragment::class.java.getSimpleName()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        auth = FirebaseAuth.getInstance()

        val binding = DataBindingUtil.inflate<CadastroBasicoFragmentBinding>(inflater, R.layout.cadastro_basico_fragment, container, false)

        userLogin = binding.emailCadastro
        userPassword = binding.senhaCadastro


        binding.buttonProximo.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER"){view: View ->
            singUpUser(view)
        }
        return binding.root
    }


    private fun singUpUser(view: View ){
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
        auth.createUserWithEmailAndPassword(userLogin.text.toString(), userPassword.text.toString())
            .addOnCompleteListener(this.activity!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LOGTAG, "createUserWithEmail:success")
                    view.findNavController().navigate(CadastroBasicoFragmentDirections.action_cadastroBasicoFragment_to_uploadPhotoFragment())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LOGTAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        App.context, getString(R.string.auth_failed),
                        Toast.LENGTH_SHORT).show()
                }

            }
    }


}



