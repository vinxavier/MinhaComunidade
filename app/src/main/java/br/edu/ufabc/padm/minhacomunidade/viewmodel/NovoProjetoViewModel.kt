package br.edu.ufabc.padm.minhacomunidade.viewmodel

import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import br.edu.ufabc.padm.minhacomunidade.model.repository.ProjectRepository.newProject

class NovoProjetoViewModel {
    fun novoProjeto(projeto: Projeto){
        newProject( projeto)
    }
}