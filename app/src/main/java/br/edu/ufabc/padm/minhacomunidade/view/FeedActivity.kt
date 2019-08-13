package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FeedActivity:AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity)
        addButton = findViewById(R.id.addButton)
        populateProjetos()
    }

    private fun populateProjetos() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ProjetoAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.recycle_projetos).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }


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
        return true
    }

}