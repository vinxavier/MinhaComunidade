package br.edu.ufabc.padm.minhacomunidade.model.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Usuario(
    var email: String = "",
    var nome: String = "",
    var cidade: String = "",
    var telefone: String = "",
    var groups:ArrayList<String> = ArrayList()
)