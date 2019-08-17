package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ProjectRepository {
    private var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private val LOGTAG = ProjectRepository::class.java.getSimpleName()



    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.PROJECT_TABLE)
        auth = FirebaseAuth.getInstance()
    }

    fun newProject(projeto: Projeto){
        val projetoId = database.push().key
        projeto.createdBy = auth.currentUser!!.uid


        database.child(projeto.grupo).child(projetoId!!).setValue(projeto)
            .addOnSuccessListener {
                // Write was successful!
                // ...
                Log.w(LOGTAG, "Projeto salvo com sucesso")
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e(LOGTAG, it.toString())
            }
    }

}