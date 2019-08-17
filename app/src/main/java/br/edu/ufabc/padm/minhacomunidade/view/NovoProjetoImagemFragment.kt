package br.edu.ufabc.padm.minhacomunidade.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.NovoProjetoImagemFragmentBinding

class NovoProjetoImagemFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<NovoProjetoImagemFragmentBinding>(
            inflater,
            R.layout.novo_projeto_imagem_fragment,
            container,
            false
        )

        binding.confirmButton.setOnClickListener { view: View ->
            view.findNavController().navigate(NovoProjetoImagemFragmentDirections.action_novoProjetoImagemFragment2_to_feedFragment())
        }

        binding.pularButton.setOnClickListener { view: View ->
            view.findNavController().navigate(NovoProjetoImagemFragmentDirections.action_novoProjetoImagemFragment2_to_feedFragment())
        }

        return binding.root
    }

}