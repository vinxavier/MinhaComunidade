package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.entity.UserGroups
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object GroupsRepository {
    private var database: DatabaseReference


    private val LOGTAG = GroupsRepository::class.java.getSimpleName()

    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.GROUPS_USER_TABLE)
    }

    fun setUserGroups(groups: ArrayList<String>, userId: String){
        val uid = database.push().key

        val userGroups = UserGroups(groups,userId)

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