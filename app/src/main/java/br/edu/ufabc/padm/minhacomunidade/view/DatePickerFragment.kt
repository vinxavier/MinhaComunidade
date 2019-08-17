package br.edu.ufabc.padm.minhacomunidade.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import br.edu.ufabc.padm.minhacomunidade.App
import java.util.*

class DatePickerFragment(date: TextView) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var dateEditable: TextView = date

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(this.activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int){
        // Do something with the date chosen by the user
        dateEditable.text =  day.toString() + " / " + month.toString() + " / " + year.toString()

    }

}