package br.edu.ufabc.padm.minhacomunidade.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.minhacomunidade.R

class NovoProjeto2Activity: AppCompatActivity() {

    private lateinit var proximoButton: Button
    private lateinit var pularButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_projeto_2)

        init()
    }

    fun init(){
        proximoButton.findViewById<Button>(R.id.button_proximo)
        
    }
}