package br.edu.ufabc.padm.minhacomunidade.model.entity

import android.media.Image
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Projeto(var titulo:String="", var grupo:String = "", var endr:String="",
                   var descricao:String="", var dataEvent: String="", var minVol: Int=1, var vol: Int=0, var createdBy: String = "",
                   var images: ArrayList<Image> = ArrayList())