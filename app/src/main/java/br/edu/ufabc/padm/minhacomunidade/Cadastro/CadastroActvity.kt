package br.edu.ufabc.padm.minhacomunidade.Cadastro


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroActivityBinding


class CadastroActvity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<CadastroActivityBinding>(this, R.layout.cadastro_activity)

    }



}