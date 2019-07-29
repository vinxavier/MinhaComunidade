package br.edu.ufabc.padm.minhacomunidade

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class BuscaActivity : AppCompatActivity() {

    private Spinner spinnerProjeto = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        spinnerProjeto = (Spinner) findViewById(R.id.spinnerProjeto);

        ArrayAdapter<CharSequence> adapter_projeto = ArrayAdapter.createFromResource(this, R.array.dias,
        android.R.layout.simple_spinner_item);

        adapter_projeto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProjeto.setAdapter(adapter_projeto);
    }
}
