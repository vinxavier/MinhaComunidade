package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.entity.Usuario
import br.edu.ufabc.padm.minhacomunidade.model.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class PerfilViewModel: ViewModel(){

    fun retrieveUsuario(): Usuario? {
        if( UserRepository.getUser() != null){
            return UserRepository.getUser()!!
        }
        return null
    }
}