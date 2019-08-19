package br.edu.ufabc.padm.minhacomunidade.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import br.edu.ufabc.padm.minhacomunidade.viewmodel.PerfilViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.mikhaellopez.circularimageview.CircularImageView
import com.google.android.gms.tasks.OnSuccessListener




class PerfilActivity: AppCompatActivity(){

    private lateinit var viewModel: PerfilViewModel

    private lateinit var nomeTextView: TextView

    private lateinit var emailTextView: TextView

    private lateinit var cidadeTextView: TextView

    private lateinit var telefoneTextView: TextView

    private lateinit var fotoPerfil: CircularImageView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PerfilViewModel::class.java)

        auth = FirebaseAuth.getInstance()

        setContentView(R.layout.perfil_activity)

        displayData()
    }

    private fun displayData() {

        val storageReference = FirebaseStorage.getInstance().getReference("profilepics/"+auth.currentUser!!.uid+"profilepic.jpg")



        nomeTextView = findViewById(R.id.nome_text)

        emailTextView = findViewById(R.id.email_text)

        cidadeTextView = findViewById(R.id.cidade_text)

        telefoneTextView = findViewById(R.id.telefone_text)

        fotoPerfil = findViewById(R.id.perfil_foto)

        val usuario: Usuario? = viewModel.retrieveUsuario()

        storageReference.downloadUrl.addOnSuccessListener{
            Glide.with(this).load(it).into(fotoPerfil)
            Log.i("Perfil", "Uri = $it")
        }.addOnFailureListener{
            Log.i("Perfil", "exception: $it")
        }

        if(usuario == null){
            nomeTextView.text = "Usuário não encontrado"
        }
        else{
            nomeTextView.text = usuario.nome
            emailTextView.text = usuario.email
            cidadeTextView.text = usuario.cidade
            telefoneTextView.text = usuario.telefone


        }


    }
}