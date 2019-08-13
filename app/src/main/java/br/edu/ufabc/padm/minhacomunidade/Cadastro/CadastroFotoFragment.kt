package br.edu.ufabc.padm.minhacomunidade.Cadastro


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.CadastroFotoFragmentBinding


class CadastroFotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<CadastroFotoFragmentBinding>(inflater, R.layout.cadastro_foto_fragment, container, false)

        binding.profilePicture.setOnClickListener(){

            //TODO: Criar método para upload de foto e passar como argumento
        }

        binding.pularBtn.setOnClickListener(){
            view: View -> view.findNavController().navigate(CadastroFotoFragmentDirections.action_uploadPhotoFragment_to_cadastroGruposFragment())
        }

        binding.salvarBtn.setOnClickListener(){
                view: View -> view.findNavController().navigate(CadastroFotoFragmentDirections.action_uploadPhotoFragment_to_cadastroGruposFragment())
            //TODO: passar a imagem como parametro
        }

        return binding.root
    }


}
