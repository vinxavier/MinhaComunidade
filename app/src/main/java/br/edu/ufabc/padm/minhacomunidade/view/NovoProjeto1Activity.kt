package br.edu.ufabc.padm.minhacomunidade.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.edu.ufabc.padm.minhacomunidade.App
import br.edu.ufabc.padm.minhacomunidade.R
import br.edu.ufabc.padm.minhacomunidade.model.entity.Projeto
import br.edu.ufabc.padm.minhacomunidade.model.repository.FirebaseContract
import br.edu.ufabc.padm.minhacomunidade.model.repository.ProjectRepository
import br.edu.ufabc.padm.minhacomunidade.viewmodel.NovoProjetoViewModel
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class NovoProjeto1Activity: AppCompatActivity(){
    // Initialize the AutocompleteSupportFragment.

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var autocompleteFragment: AutocompleteSupportFragment
    private lateinit var confirmBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var dateBtn: ImageButton
    private lateinit var incrementBtn: ImageButton
    private lateinit var decrementBtn: ImageButton
    private lateinit var tituloInput: EditText
    private lateinit var descricaoInput: EditText
    private lateinit var gruposInput: Spinner
    private lateinit var dateInput: EditText
    private lateinit var minVolInput: EditText
    private var projeto: Projeto = Projeto()


    private var minVol = 1

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
        database = FirebaseDatabase.getInstance().getReference(FirebaseContract.PROJECT_TABLE)
        auth = FirebaseAuth.getInstance()


        autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.local_autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment!!.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                projeto.endr = place.address.toString()
                Log.i(LOGTAG, "Place: " + place.name + ", " + place.address)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(LOGTAG, "An error occurred: $status")
            }
        })


        tituloInput = findViewById(R.id.tituloProjeto)
        gruposInput = findViewById(R.id.selecionar_grupo)
        descricaoInput = findViewById(R.id.descricao_projeto)
        confirmBtn = findViewById(R.id.confirm_button)
        cancelBtn = findViewById(R.id.cancel_button)
        dateBtn = findViewById(R.id.data_button)
        dateInput = findViewById(R.id.data_event)
        minVolInput = findViewById(R.id.minVol)
        incrementBtn = findViewById(R.id.plus_vol_button)
        decrementBtn = findViewById(R.id.menus_vol_button)

    }


    override fun onResume() {
        super.onResume()

        confirmBtn.setOnClickListener {
            salvarProjeto()
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

        incrementBtn.setOnClickListener {
            minVol +=1
            minVolInput.setText(minVol.toString())
        }

        decrementBtn.setOnClickListener {
            minVol -=1
            minVolInput.setText(minVol.toString())
        }

    }

    fun showDateDialog(){
        val newFragment = DatePickerFragment(dateInput)
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun salvarProjeto() {
        if (tituloInput.text.toString().isEmpty()) {
            tituloInput.error = getString(R.string.enter_titulo)
            tituloInput.requestFocus()
            return
        }
        if (descricaoInput.text.toString().isEmpty()) {
            descricaoInput.error = getString(R.string.enter_description)
            descricaoInput.requestFocus()
            return
        }
        if (!(dateInput.text.toString().matches("""\d{2}\ / \d{2}\ / \d{4}""".toRegex()))) {
            dateInput.error = getString(R.string.enter_valid_date)
            dateInput.requestFocus()
            return
        }

        projeto.titulo = tituloInput.text.toString()
        projeto.dataEvent = dateInput.text.toString()
        projeto.descricao = descricaoInput.text.toString()
        projeto.grupo = gruposInput.selectedItem.toString()
        projeto.minVol = minVolInput.text.toString().toInt()

        val projetoId = database.push().key
        projeto.createdBy = auth.currentUser!!.uid



        database.child(projeto.grupo).child(projetoId!!).setValue(projeto)
            .addOnSuccessListener {
                // Write was successful!

                startActivity(Intent(this, NovoProjeto2Activity::class.java))
                Log.w(LOGTAG, "Projeto salvo com sucesso")
                finish()
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }


    }


}


