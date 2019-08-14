package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object UserRepository {
    private var database: DatabaseReference

    private val LOGTAG = UserRepository::class.java.getSimpleName()


    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.USER_TABLE)
    }

    fun newUser(userId: String, user: Usuario){

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