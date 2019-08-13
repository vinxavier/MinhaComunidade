package br.edu.ufabc.padm.minhacomunidade.Cadastro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Grupo

class GrupoAdapter(private val context: Context,
                   private val dataSource: ArrayList<Grupo>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item

        val rowView = inflater.inflate(R.layout.cadastro_grupos_fragment, parent, false)

        return rowView
    }
}