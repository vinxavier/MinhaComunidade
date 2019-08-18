package br.edu.ufabc.padm.minhacomunidade.viewmodel

import androidx.lifecycle.ViewModel
import br.edu.ufabc.padm.minhacomunidade.model.repository.ProjectRepository.updateProjectDAO

class FeedViewModel:ViewModel() {
    fun updateProjects(){
        updateProjectDAO()
    }
}