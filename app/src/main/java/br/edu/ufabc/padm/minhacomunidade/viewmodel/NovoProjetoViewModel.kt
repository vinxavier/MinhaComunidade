package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NovoProjetoViewModel:ViewModel(){

    val title = MutableLiveData<String>()
    val descricao = MutableLiveData<String>()
    val data = MutableLiveData<String>()
    val grupos = MutableLiveData<String>()
    val minVol = MutableLiveData<String>()




}