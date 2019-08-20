package br.edu.ufabc.padm.minhacomunidade.model.repository

import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.model.dao.ProjetoDAO
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import com.google.firebase.database.*


object ProjectRepository {

    private var database: DatabaseReference


    private val LOGTAG = ProjectRepository::class.java.getSimpleName()



    init{
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.PROJECT_TABLE)
    }

    val projectsListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            for(projectSnapshot in dataSnapshot.children) {
                if (dataSnapshot.getValue(Projeto::class.java) != null) {
                    ProjetoDAO.instance.add(dataSnapshot.getValue(Projeto::class.java)!!)
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(LOGTAG, "loadPost:onCancelled", databaseError.toException())
            // ...
        }
    }

    fun updateProjectDAO(){
        ProjetoDAO.instance.clear()
        val actualUser = UserRepository.getUser()
        if (actualUser!=null) {
            val userGroup = actualUser.groups
            for (group in userGroup) {
                val projetosReference = database.child(group)
                projetosReference.addListenerForSingleValueEvent(projectsListener)
            }
        }
    }

    fun newProject(projeto: Projeto){
        database.push().setValue(projeto)
    }
}