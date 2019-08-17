package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FeedActivity:AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addButton: FloatingActionButton
    lateinit var profileImageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity)
        addButton = findViewById(R.id.addButton)
        populateProjetos()
    }

    private fun populateProjetos() {
        auth = FirebaseAuth.getInstance()

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

        profileImageReference.getFile((auth.currentUser!!.uid+"profilepic.jpg").toUri())






    }

    override fun onResume() {
        super.onResume()
        addButton.setOnClickListener{
            val intent = Intent(this, NovoProjeto1Activity::class.java)

            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feed_menu, menu)

        //menu?.getItem(R.id.action_perfil)?.icon =

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out_action) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

}