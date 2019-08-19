package br.edu.ufabc.padm.minhacomunidade.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.edu.ufabc.padm.minhacomunidade.viewmodel.PerfilViewModel

class ProfileActivity: AppCompatActivity(){

    private lateinit var viewModel: PerfilViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PerfilViewModel::class.java)

        displayData()
    }

    private fun displayData() {

    }
}