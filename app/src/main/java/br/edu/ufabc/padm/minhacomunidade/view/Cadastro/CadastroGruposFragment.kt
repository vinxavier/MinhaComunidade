package br.edu.ufabc.padm.minhacomunidade.view.Cadastro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroGruposFragmentBinding
import br.edu.ufabc.padm.minhacomunidade.model.entity.Grupo
import androidx.navigation.findNavController


class CadastroGruposFragment : Fragment() {

    private val grupos: MutableList<Grupo> = mutableListOf(
        Grupo("ABC"),
        Grupo("Zona Norte SP"),
        Grupo("Zona Leste SP"),
        Grupo("Zona Sul SP"),
        Grupo("Zona Oeste SP"),
        Grupo("Centro SP")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<CadastroGruposFragmentBinding>(inflater, R.layout.cadastro_grupos_fragment, container, false)

        binding.finalizarBtn.setOnClickListener(){
            view: View -> view.findNavController().navigate(CadastroGruposFragmentDirections.action_cadastroGruposFragment_to_feedActivity())
        }

        return binding.root

    }


}
