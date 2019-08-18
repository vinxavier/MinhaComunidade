package br.edu.ufabc.padm.minhacomunidade.model.dao

import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import java.util.ArrayList

object ProjetoDAO{
    private val projetos:MutableList<Projeto> = ArrayList()
    val instance = this


    fun add(projeto: Projeto) {
        projetos.add(projeto)
    }

    fun size(): Int {
        return projetos.size
    }

    fun clear(){
        projetos.clear()
    }

    fun getItemAt(pos: Int): Projeto {
        return projetos[pos]
    }

}