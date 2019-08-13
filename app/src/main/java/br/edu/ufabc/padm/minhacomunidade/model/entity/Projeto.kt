package br.edu.ufabc.padm.minhacomunidade.model.entity

import android.media.Image


data class Projeto(var titulo:String="", var grupo:Grupo = Grupo(""), var tag: String="", var endr:String="",
                   var descricao:String="", var dataEvent: String="", var minVol: Int=0, var vol: Int=0,
                   var images: ArrayList<Image> = ArrayList())