package br.edu.ufabc.padm.minhacomunidade

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class FeedActivity:AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity)
        populateProjetos()
    }

    private fun populateProjetos() {
        listView = findViewById(R.id.list_projetos)

        listView.adapter = ProjetoAdapter()

    }

    override fun onResume() {
        super.onResume()

        (listView.adapter as BaseAdapter).notifyDataSetChanged()
    }
}