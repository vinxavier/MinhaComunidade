package br.edu.ufabc.padm.minhacomunidade

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import java.util.*
import com.google.android.libraries.places.internal.i
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener




class NovoProjeto1Activity: AppCompatActivity() {
    // Initialize the AutocompleteSupportFragment.
    lateinit var autocompleteFragment: AutocompleteSupportFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_projeto_1)
        init()
    }

    fun init(){
        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = supportFragmentManager.findFragmentById(R.id.local_autocomplete_fragment) as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

    }

    override fun onResume(){
        super.onResume()
        // Set up a PlaceSelectionListener to handle the response.
        //autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
          //  override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
            }

        //})
   // }

}