package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.repository.GroupsRepository.setUserGroups

class CadastroGruposViewModel : ViewModel() {
    fun definirGruposUsuario(groups: ArrayList<String>, userId: String){
        setUserGroups(groups, userId)
    }
}