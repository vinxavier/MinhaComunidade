package br.edu.ufabc.padm.minhacomunidade.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?){
       super.onCreate(savedInstanceState)

       auth = FirebaseAuth.getInstance()
       val currentUser = auth.currentUser

       val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

       
   }
}
