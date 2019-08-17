package br.edu.ufabc.padm.minhacomunidade.view



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.view.Cadastro.CadastroActvity
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.LandingFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LandingFragment : Fragment() {

    private lateinit var loginBtn: Button
    private lateinit var cadastroBtn: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        updateUI(currentUser)



        val binding = DataBindingUtil.inflate<LandingFragmentBinding>(inflater, R.layout.landing_fragment, container, false)
        loginBtn = binding.loginBtn
        cadastroBtn = binding.cadastrarBtn

        loginBtn.setOnClickListener(){
            view: View -> view.findNavController().navigate(LandingFragmentDirections.action_landingFragment_to_loginFragment())
        }

        binding.cadastrarBtn.setOnClickListener(){
            view: View -> view.findNavController().navigate(LandingFragmentDirections.action_landingFragment_to_cadastroBasicoFragment())
        }

        return binding.root

    }


    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser!=null){
            findNavController().navigate(LandingFragmentDirections.action_landingFragment_to_feedFragment())
        }
    }


}
