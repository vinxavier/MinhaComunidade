package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import br.edu.ufabc.padm.minhacomunidade.model.repository.UserRepository

class CadastroViewModel : ViewModel() {

    fun salvarUsuario(user: Usuario){
        return UserRepository.newUser(user)
    }
}