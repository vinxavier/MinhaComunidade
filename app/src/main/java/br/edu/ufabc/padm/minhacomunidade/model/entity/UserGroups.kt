package br.edu.ufabc.padm.minhacomunidade.model.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserGroups (
    val groups:ArrayList<String>,
    val userUid: String
)