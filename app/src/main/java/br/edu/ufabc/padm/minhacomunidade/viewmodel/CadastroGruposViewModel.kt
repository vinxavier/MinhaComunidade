package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.repository.UserRepository.setUserGroups

class CadastroGruposViewModel : ViewModel() {
    fun definirGruposUsuario(groups: ArrayList<String>){
        setUserGroups(groups)
    }
}