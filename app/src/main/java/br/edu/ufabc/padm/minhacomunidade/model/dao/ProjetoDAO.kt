package br.edu.ufabc.padm.minhacomunidade.model.dao

import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import java.util.ArrayList

object ProjetoDAO{
    private val projetos:MutableList<Projeto> = ArrayList()
    val instance = this

    init {
        loadTestData()
    }

    private fun loadTestData() {
        var p: Projeto

        p = Projeto()

        p.titulo = "Pintar muro do lar dos idosos"
        p.descricao = "Iremos pintar o muro"
        p.endr = "Rua José Fernandes, 453"
        p.dataEvent = "25/03/2019"
        p.grupo = "ABC"
        p.minVol = 10
        p.vol = 6


        projetos.add(p)

        p = Projeto()

        p.titulo = "Instalar Lixeiras no Bairro"
        p.descricao = "Iremos fazer a instalação de lixeiras"
        p.endr = "Rua Pitô Fagundes, 43"
        p.dataEvent = "10/03/2019"
        p.grupo = "ABC"
        p.minVol = 10
        p.vol = 2


        projetos.add(p)

    }

    fun add(projeto: Projeto) {
        projetos.add(projeto)
    }

    fun size(): Int {
        return projetos.size
    }

    fun getItemAt(pos: Int): Projeto {
        return projetos[pos]
    }

}