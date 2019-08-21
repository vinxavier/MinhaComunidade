package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import br.edu.ufabc.padm.minhacomunidade.model.repository.ProjectRepository

class NovoProjetoViewModel:ViewModel(){

    val title = MutableLiveData<String>()
    val descricao = MutableLiveData<String>()
    val data = MutableLiveData<String>()
    val grupos = MutableLiveData<String>()
    val minVol = MutableLiveData<String>()

    fun salvarProjeto(projeto: Projeto){
        return ProjectRepository.newProject(projeto)
    }


}