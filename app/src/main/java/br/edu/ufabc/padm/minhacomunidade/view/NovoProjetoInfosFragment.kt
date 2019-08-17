package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.databinding.NovoProjetoInfosFragmentBinding
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import java.util.*


class NovoProjetoInfosFragment: Fragment(), PlaceSelectionListener {
    // Initialize the AutocompleteSupportFragment.
    lateinit var autocompleteFragment: AutocompleteSupportFragment
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button

    private var apiKey = "AIzaSyC1oop6vb7GtXqcQbw4-FIdp5DU2GCt1FM"

    private val LOGTAG = NovoProjetoInfosFragment::class.java.getSimpleName()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<NovoProjetoInfosFragmentBinding>(inflater, R.layout.novo_projeto_infos_fragment, container, false)

        init(binding)

        return binding.root
    }


    fun init(binding: NovoProjetoInfosFragmentBinding){
        if (!Places.isInitialized()) {
            Places.initialize(App.context,apiKey)
        }

        autocompleteFragment =
            requireFragmentManager().findFragmentById(R.id.local_autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment!!.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i(LOGTAG, "Place: " + place.name + ", " + place.id)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(LOGTAG, "An error occurred: $status")
            }
        })


        binding.confirmButton.setOnClickListener{
            view: View -> view.findNavController().navigate(NovoProjetoInfosFragmentDirections.action_novoProjetoInfosFragment_to_feedFragment())
        }

        binding.cancelButton.setOnClickListener{
            view: View -> view.findNavController().navigate(NovoProjetoInfosFragmentDirections.action_novoProjetoInfosFragment_to_feedFragment())
        }

        confirmBtn = binding.confirmButton
        cancelBtn = binding.cancelButton

    }

    override fun onPlaceSelected(p0: Place) {

        Toast.makeText(requireContext(),""+p0!!.name+p0!!.latLng,Toast.LENGTH_LONG).show()
    }

    override fun onError(status: Status) {
        Toast.makeText(requireContext(),""+status.toString(),Toast.LENGTH_LONG).show()
    }

}