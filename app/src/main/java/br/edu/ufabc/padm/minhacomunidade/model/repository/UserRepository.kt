package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Grupo
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object UserRepository {
    private var database: DatabaseReference

    private val LOGTAG = UserRepository::class.java.getSimpleName()


    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.USERS_TABLE)
    }

    fun newUser(user: Usuario){
        val userId = database.push().key


        database.child(userId!!).setValue(user)
            .addOnSuccessListener {
                // Write was successful!
                // ...
                Log.w(LOGTAG, "Usuario salvo com sucesso")
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e(LOGTAG, it.toString())
            }
    }

}