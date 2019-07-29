package br.edu.ufabc.padm.minhacomunidade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.model.dao.ProjetoDAO

class ProjetoAdapter : RecyclerView.Adapter<ProjetoAdapter.ProjetoHolder>() {
    override fun getItemCount(): Int {
        return ProjetoDAO.instance.size()
    }

    class ProjetoHolder(val localView: View) : RecyclerView.ViewHolder(localView){
        val titulo = localView.findViewById<TextView>(R.id.tituloProjeto)
        val dataEv = localView.findViewById<TextView>(R.id.dataProjeto)
        val perc = localView.findViewById<ProgressBar>(R.id.minVol)
        val lperc = localView.findViewById<TextView>(R.id.prcLabel)
        val currP = localView.findViewById<TextView>(R.id.currPeople)
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProjetoAdapter.ProjetoHolder {
        // create a new view
        val projView = LayoutInflater.from(parent.context)
            .inflate(R.layout.projeto_item, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return ProjetoHolder(projView)
    }

    override fun onBindViewHolder(holder: ProjetoHolder, position: Int) {
        val projeto = ProjetoDAO.instance.getItemAt(position)

        holder.titulo.text = projeto.titulo
        holder.dataEv.text = projeto.dataEvent
        holder.perc.progress = projeto.vol*100/projeto.minVol
        holder.lperc.text = (projeto.vol*100/projeto.minVol).toString() + "%"
        holder.currP.text = projeto.vol.toString()+" / " + projeto.minVol.toString()
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

}