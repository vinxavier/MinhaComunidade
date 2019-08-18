package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.minhacomunidade.model.entity.UserGroups
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object UserRepository {
    private var database: DatabaseReference

    private val LOGTAG = UserRepository::class.java.getSimpleName()
    private var auth: FirebaseAuth

    private var actualUser: Usuario? = null


    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.USER_TABLE)
        auth = FirebaseAuth.getInstance()
    }

    fun newUser(user: Usuario){

        database.child(auth.currentUser!!.uid).setValue(user)
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

    fun setUserGroups(groups: ArrayList<String>){
        val groupsRef = database.child(auth.currentUser!!.uid).child("groups")


        groupsRef.setValue(groups)
            .addOnSuccessListener {
                // Write was successful!
                // ...
                Log.w(LOGTAG, "Grupos salvos com sucesso")
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e(LOGTAG, it.toString())
            }

    }

    val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            if( dataSnapshot.getValue(Usuario::class.java)!= null) {
                actualUser = dataSnapshot.getValue(Usuario::class.java)!!
            }
                // ...
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(LOGTAG, "loadPost:onCancelled", databaseError.toException())
            // ...
        }
    }

    fun getUser():Usuario?{
        database.child(auth.currentUser!!.uid).addListenerForSingleValueEvent(userListener)
        if(actualUser!=null) {
            Log.w(LOGTAG, actualUser!!.nome)
        }else{
            Log.w(LOGTAG, "User ta null")
        }
        return actualUser
    }



}