package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ProjectRepository {
    private var database: DatabaseReference

    private val LOGTAG = ProjectRepository::class.java.getSimpleName()


    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.PROJECT_TABLE)
    }

    fun newProject(projeto: Projeto){
        val projetoId = database.push().key


        database.child(projetoId!!).setValue(projeto)
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