package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.entity.UserGroups
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object GroupsRepository {
    private var database: DatabaseReference


    private val LOGTAG = GroupsRepository::class.java.getSimpleName()
    private lateinit var auth: FirebaseAuth

    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.GROUPS_USER_TABLE)

        auth = FirebaseAuth.getInstance()
    }

    fun setUserGroups(groups: ArrayList<String>){
        val uid = database.push().key


        val userGroups = UserGroups(groups,auth.currentUser!!.uid)

        database.child(uid!!).setValue(userGroups)
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

}