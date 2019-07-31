package br.edu.ufabc.padm.minhacomunidade


import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_busca.*

import kotlinx.android.synthetic.main.activity_main.*

import android.widget.ArrayAdapter
import android.widget.Spinner

class BuscaActivity : AppCompatActivity() {

    private lateinit var spinnerProjeto: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_busca);
        //spinnerProjeto = (Spinner) findViewById(R.id.spinnerProjeto);

        //ArrayAdapter<CharSequence> adapter_projeto = ArrayAdapter.createFromResource(this, R.array.dias,
        //android.R.layout.simple_spinner_item);

        //adapter_projeto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //spinnerProjeto.setAdapter(adapter_projeto);
    }
}
