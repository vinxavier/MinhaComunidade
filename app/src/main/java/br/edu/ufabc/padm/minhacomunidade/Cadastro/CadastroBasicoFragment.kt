package br.edu.ufabc.padm.minhacomunidade.Cadastro


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroBasicoFragmentBinding

class CadastroBasicoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<CadastroBasicoFragmentBinding>(inflater, R.layout.cadastro_basico_fragment, container, false)


        binding.buttonProximo.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {view: View ->
            view.findNavController().navigate(CadastroBasicoFragmentDirections.action_cadastroBasicoFragment_to_uploadPhotoFragment())
        }
        return binding.root
        }
    }



