package br.edu.ufabc.padm.minhacomunidade.view.Cadastro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroGruposFragmentBinding
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.App


class CadastroGruposFragment : Fragment() {

    lateinit var listView: ListView

    private val LOGTAG = CadastroGruposFragment::class.java.getSimpleName()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<CadastroGruposFragmentBinding>(inflater, R.layout.cadastro_grupos_fragment, container, false)

        listView = binding.scrollView2

        // register the adapter
        listView.adapter = ArrayAdapter(
            App.context,
            R.layout.groups_list_item,
            resources.getStringArray(R.array.groups_list))


        //TODO: adicionar cada item selecionado à ArrayList
        binding.finalizarBtn.setOnClickListener{
            if(listView!=null) {
                var groups: ArrayList<String> = listView.selectedItem as ArrayList<String>
                Log.i("GRUPOTENT", groups.get(0))
                println(groups)
            }
            it.findNavController().navigate(CadastroGruposFragmentDirections.action_cadastroGruposFragment_to_feedActivity())
        }

        return binding.root

    }


}
