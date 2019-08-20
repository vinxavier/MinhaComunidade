package br.edu.ufabc.padm.minhacomunidade.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.OnItemClickListener
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.addOnItemClickListener
import br.edu.ufabc.padm.minhacomunidade.model.dao.ProjetoDAO
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import br.edu.ufabc.padm.minhacomunidade.model.repository.FirebaseContract
import br.edu.ufabc.padm.minhacomunidade.services.FetchProjetosContract
import br.edu.ufabc.padm.minhacomunidade.services.FetchProjetosService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FeedActivity:AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addButton: FloatingActionButton
    lateinit var profileImageReference: StorageReference
    private var actualUser: Usuario? = null
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.feed_activity)
        addButton = findViewById(R.id.addButton)
        populateProjetos()
    }

    private fun populateProjetos() {

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.USER_TABLE)



        database.child(auth.currentUser!!.uid).addListenerForSingleValueEvent(userListener)


        viewManager = LinearLayoutManager(this)
        viewAdapter = ProjetoAdapter()

        profileImageReference = FirebaseStorage.getInstance().getReference("profilepics")

        recyclerView = findViewById<RecyclerView>(R.id.recycle_projetos).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        //profileImageReference.getFile((auth.currentUser!!.uid+"profilepic.jpg").toUri())


    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser==null){
            startActivity(Intent(App.context, MainActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter!!.notifyDataSetChanged()

        recyclerView.addOnItemClickListener(object: OnItemClickListener{


            override fun onItemClicked(position: Int, view: View) {

                val projeto = ProjetoDAO.getItemAt(position)
                iniciarActivity(projeto)

            }
        })


        addButton.setOnClickListener{
            val intent = Intent(this, NovoProjeto1Activity::class.java)

            startActivity(intent)
        }

    }

    fun iniciarActivity(projeto:Projeto){
        val intent = Intent(this, DetalheProjetoActivity::class.java)
        intent.putExtra("PROJETO", projeto)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feed_menu, menu)

        //menu?.getItem(R.id.action_perfil)?.icon

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out_action) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            return true
        }else if(item.itemId == R.id.action_perfil){
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


    val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            if( dataSnapshot.getValue(Usuario::class.java)!= null) {
                actualUser = dataSnapshot.getValue(Usuario::class.java)!!
                callProjects()
                Log.w("LOGFEED", actualUser!!.nome)
            }
            // ...
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w("LOG FEED ACTIVITY", "loadPost:onCancelled", databaseError.toException())
            // ...
        }
    }

    private fun callProjects() {
        if (actualUser!=null) {
            val userGroup = actualUser!!.groups
            fetchProjetos(userGroup)
        }
    }


    private fun fetchProjetos(grupos: ArrayList<String>){
        App.registerBroadcast(fetchProjectsReceiver, IntentFilter(FetchProjetosContract.FETCHED_PROJETOS))
        val intent =   Intent(this, FetchProjetosService::class.java)
        intent.action = FetchProjetosContract.FETCH_PROJETOS
        intent.putExtra(FetchProjetosContract.FETCH_PROJETOS_EXTRA, grupos)
        App.context.startService(intent)
    }

    private val fetchProjectsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.apply {
                recyclerView.adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        App.unregisterBroadcast(fetchProjectsReceiver)

    }


}