package br.edu.ufabc.padm.minhacomunidade.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto


class DetalheProjetoActivity: AppCompatActivity(){

    private lateinit var projeto:Projeto

    private lateinit var tituloTextView: TextView
    private lateinit var grupoTextView: TextView
    private lateinit var enderecoTextView: TextView
    private lateinit var descricaoTextView: TextView
    private lateinit var dataTextView: TextView
    private lateinit var numNecesTextView: TextView
    private lateinit var numAtualTextView: TextView
    private lateinit var criadorTextView: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras


        if (extras != null) {
            projeto = extras.getSerializable("Projeto") as Projeto
            // and get whatever type user account id is
        }

        setContentView(R.layout.detalhes_projeto_activity)

        init()
    }

    fun init(){

        tituloTextView = findViewById(R.id.titulo_text)
        grupoTextView = findViewById(R.id.grupo_text)
        enderecoTextView = findViewById(R.id.endereco_text)
        descricaoTextView = findViewById(R.id.descricao_text)
        dataTextView = findViewById(R.id.data_text)
        numNecesTextView = findViewById(R.id.num_neces_text)
        numAtualTextView = findViewById(R.id.num_atual_text)
        criadorTextView = findViewById(R.id.criador_text)



        if(projeto!=null) {
            tituloTextView.text = projeto!!.titulo
            grupoTextView.text = projeto.grupo
            enderecoTextView.text = projeto.endr
            descricaoTextView.text = projeto.descricao
            dataTextView.text = projeto.dataEvent
            numNecesTextView.text = projeto.minVol.toString()
            numAtualTextView.text = projeto.vol.toString()
            criadorTextView.text = projeto.createdBy
        }
    }
}