package br.edu.ufabc.padm.minhacomunidade.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ufabc.padm.minhacomunidade.R
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener




class NovoProjeto1Activity: AppCompatActivity(),PlaceSelectionListener {
    // Initialize the AutocompleteSupportFragment.
    lateinit var autocompleteFragment: PlaceAutocompleteFragment
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_projeto_1)
        init()
    }

    fun init(){
        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = fragmentManager.findFragmentById(R.id.local_autocomplete_fragment) as PlaceAutocompleteFragment
        // Specify the types of place data to return.
        autocompleteFragment.setOnPlaceSelectedListener(this)

        confirmBtn = findViewById(R.id.confirm_button)
        cancelBtn = findViewById(R.id.cancel_button)

    }

    override fun onPlaceSelected(p0: Place?) {

        Toast.makeText(applicationContext,""+p0!!.name+p0!!.latLng,Toast.LENGTH_LONG).show();
    }

    override fun onError(status: Status) {
        Toast.makeText(applicationContext,""+status.toString(),Toast.LENGTH_LONG).show();
    }

    override fun onResume() {
        super.onResume()

        confirmBtn.setOnClickListener {
            startActivity(Intent(this, NovoProjeto2Activity::class.java))
        }

        cancelBtn.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

    }

}