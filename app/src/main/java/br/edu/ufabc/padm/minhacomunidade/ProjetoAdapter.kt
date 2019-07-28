package br.edu.ufabc.padm.minhacomunidade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import android.widget.TextView
import br.edu.ufabc.padm.minhacomunidade.model.dao.ProjetoDAO

class ProjetoAdapter: BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = App.context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val localView = convertView ?: inflater.inflate(R.layout.projeto_item, null)
        val projeto = ProjetoDAO.instance.getItemAt(position)
        val titulo = localView.findViewById<TextView>(R.id.tituloProjeto)
        val dataEv = localView.findViewById<TextView>(R.id.dataProjeto)
        val perc = localView.findViewById<ProgressBar>(R.id.minVol)
        val lperc = localView.findViewById<TextView>(R.id.prcLabel)
        val currP = localView.findViewById<TextView>(R.id.currPeople)

        titulo.text = projeto.titulo
        dataEv.text = projeto.dataEvent
        perc.progress = projeto.vol*100/projeto.minVol
        lperc.text = (projeto.vol*100/projeto.minVol).toString() + "%"
        currP.text = projeto.vol.toString()+" / " + projeto.minVol.toString()

        return localView
    }

    override fun getItem(position: Int): Any {
        return ProjetoDAO.instance.getItemAt(position)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return ProjetoDAO.instance.size()
    }
}