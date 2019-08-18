package br.edu.ufabc.padm.minhacomunidade.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.model.dao.ProjetoDAO
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import br.edu.ufabc.padm.minhacomunidade.model.repository.FirebaseContract
import com.google.firebase.database.*

object FetchProjetosContract{
    const val FETCH_PROJETOS = "fetch_projetos"
    const val FETCH_PROJETOS_EXTRA = "fetch_projetos_extra"
    const val FETCHED_PROJETOS = "fetched_projetos"
}

class FetchProjetosService : IntentService("FetchProjetosService") {

    private var database: DatabaseReference
    private var grupos: ArrayList<String>? = null
    private var numbOfGroups:Int = 0
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            FetchProjetosContract.FETCH_PROJETOS ->{
                if(intent?.hasExtra(FetchProjetosContract.FETCH_PROJETOS_EXTRA)){
                    grupos = intent.getStringArrayListExtra(FetchProjetosContract.FETCH_PROJETOS_EXTRA)
                    if(grupos!=null){
                        numbOfGroups = grupos!!.size
                    }
                    fetchProjects()
                }
            }

        }
    }

    init {
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.PROJECT_TABLE)
    }

    /**
     *
     * Para cada um dos grupos do usuário, irei fazer uma requisição unica ao Firebase,
     * para que me retorne os projetos daquele grupos.
     * Irei adicionar cada um ao DAO, Em cada alteração eu mando atualizar lá na view.
     *
     */

    private fun fetchProjects() {
        if(grupos!=null) {
            ProjetoDAO.instance.clear()
            for (group in grupos!!) {
                database.child(group).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Get Post object and use the values to update the UI
                        for(projectSnapshot in dataSnapshot.children) {
                            if (projectSnapshot.getValue(Projeto::class.java) != null) {
                                val projeto = projectSnapshot.getValue(Projeto::class.java)!!
                                ProjetoDAO.instance.add(projeto)
                                sendStatus()
                                Log.w("LOGFEED", projeto.titulo)
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w("LOGFEED", "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                })
                Log.w("Grupos", database.child(group).toString())
            }
        }

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    private fun sendStatus(){
        Intent(FetchProjetosContract.FETCHED_PROJETOS).apply {
            App.sendBroadcast(this)
        }

    }


}
