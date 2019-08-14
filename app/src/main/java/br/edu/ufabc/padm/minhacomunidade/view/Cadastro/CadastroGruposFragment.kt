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
import android.util.SparseBooleanArray
import androidx.lifecycle.ViewModelProviders
import br.edu.ufabc.padm.minhacomunidade.viewmodel.CadastroGruposViewModel


class CadastroGruposFragment : Fragment() {

    lateinit var listView: ListView

    private val LOGTAG = CadastroGruposFragment::class.java.getSimpleName()
    private lateinit var viewModel: CadastroGruposViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(CadastroGruposViewModel::class.java)

        val binding = DataBindingUtil.inflate<CadastroGruposFragmentBinding>(inflater, R.layout.cadastro_grupos_fragment, container, false)

        listView = binding.scrollView2

        // register the adapter
        listView.adapter = ArrayAdapter(
            App.context,
            R.layout.groups_list_item,
            resources.getStringArray(R.array.groups_list))


        //TODO: adicionar cada item selecionado à ArrayList
        binding.finalizarBtn.setOnClickListener{
            val checked = listView.getCheckedItemPositions()
            val gruposArray = ArrayList<String>()
            for (i in 0 until checked.size()) {
                if (checked.valueAt(i)) {
                    val grupo = listView.getItemAtPosition(checked.keyAt(i)) as String
                    gruposArray.add(grupo)
                }
            }
            viewModel.definirGruposUsuario(gruposArray)
            it.findNavController().navigate(CadastroGruposFragmentDirections.action_cadastroGruposFragment_to_feedActivity())
        }

        return binding.root

    }


}
