package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
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
                                    viewType: Int): ProjetoHolder {
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
        holder.itemView.setOnClickListener {

            //it.context.startActivity(Intent(it.context,DetalheProjetoActivity::class.java).putExtra("Projeto", ProjetoDAO.instance.getItemAt(position)))
            startActivity(it.context,Intent(it.context,DetalheProjetoActivity::class.java).putExtra("Projeto", ProjetoDAO.instance.getItemAt(position)),null)
        }


    }

    override fun getItemId(p0: Int): Long {
        return 0
    }



}