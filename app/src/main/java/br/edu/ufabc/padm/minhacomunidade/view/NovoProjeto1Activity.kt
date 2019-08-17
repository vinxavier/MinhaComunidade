package br.edu.ufabc.padm.minhacomunidade.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import java.util.*


class NovoProjeto1Activity: AppCompatActivity(), PlaceSelectionListener {
    // Initialize the AutocompleteSupportFragment.
    lateinit var autocompleteFragment: AutocompleteSupportFragment
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var dateBtn: ImageButton
    private lateinit var tituloInput: EditText
    private lateinit var gruposInput: Spinner
    private lateinit var dateInput: TextView
    private var projeto: Projeto = Projeto()

    private var apiKey = "AIzaSyC1oop6vb7GtXqcQbw4-FIdp5DU2GCt1FM"

    private val LOGTAG = NovoProjeto1Activity::class.java.getSimpleName()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_projeto_1)
        init()
    }

    fun init(){
        if (!Places.isInitialized()) {
            Places.initialize(App.context,apiKey)
        }

        autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.local_autocomplete_fragment) as AutocompleteSupportFragment

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


        confirmBtn = findViewById(R.id.confirm_button)
        cancelBtn = findViewById(R.id.cancel_button)
        dateBtn = findViewById(R.id.data_button)
        dateInput = findViewById(R.id.data_event)

    }

    override fun onPlaceSelected(p0: Place) {

        Toast.makeText(applicationContext,""+p0!!.name+p0!!.latLng,Toast.LENGTH_LONG).show()
    }

    override fun onError(status: Status) {
        Toast.makeText(applicationContext,""+status.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()

        confirmBtn.setOnClickListener {
            startActivity(Intent(this, NovoProjeto2Activity::class.java))
        }

        cancelBtn.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

        dateBtn.setOnClickListener {
            showDateDialog()

        }

        dateInput.setOnClickListener {
            showDateDialog()
        }

    }

    fun showDateDialog(){
        val newFragment = DatePickerFragment(dateInput)
        newFragment.show(supportFragmentManager, "datePicker")
    }


}


