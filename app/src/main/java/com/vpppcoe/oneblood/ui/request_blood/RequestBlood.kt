package com.vpppcoe.oneblood.ui.request_blood

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vpppcoe.oneblood.MainActivity
import com.vpppcoe.oneblood.R
import com.vpppcoe.oneblood.databinding.RequestBloodBinding
import java.text.SimpleDateFormat
import java.util.*

class RequestBlood : Fragment() {

    private var _binding: RequestBloodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var bloodGroup : String? = null
    private var location : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = RequestBloodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.PATIENTDOB.setOnClickListener {
            chooseDateForDOB()
        }

        binding.PATIENTREQUIREDBLOOD.setOnClickListener {
            chooseDateForRequired()
        }


        spinnerBloodGroup()
        spinnerLocation()

        return root
    }

    private fun spinnerBloodGroup(){
        // access the items of the list
        val languages = resources.getStringArray(R.array.Blood_Group)
        // access the spinner
        val spinner = binding.spinner
        val adapter = ArrayAdapter(this.requireContext(),
            android.R.layout.simple_spinner_item, languages)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
//                    Toast.makeText(context,
//                        getString(R.string.selected_item) + " " +
//                                "" + languages[position], Toast.LENGTH_SHORT).show()

                   bloodGroup  = R.string.selected_item.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun spinnerLocation(){
        // access the items of the list
        val languages = resources.getStringArray(R.array.Location)
        // access the spinner
        val spinner = binding.spinnerLocation
        if (spinner != null) {
            val adapter = ArrayAdapter(this.requireContext(),
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(context,
//                        getString(R.string.selected_item) + " " +
//                                "" + languages[position], Toast.LENGTH_SHORT).show()

//                    binding.BLOODGROUP.text = R.string.selected_item.toString()
                    location = R.string.selected_item.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun chooseDateForDOB(){
        //This is the current date
        var myCal = Calendar.getInstance()
        var cYear = myCal.get(Calendar.YEAR)
        var cMonth = myCal.get(Calendar.MONTH)
        var cDay = myCal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                //Take the selected date
                val selectedDate = "$day/${month+1}/$year"
                //Show the Selected date
                binding.PATIENTDOB.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val bDate = sdf.parse(selectedDate)
                bDate?.let {
                    val bDateInMin = bDate.time/60000

                    val currentDateInMillis = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDateInMillis?.let {
                        val currentDateInMin = currentDateInMillis.time/60000

//                        val currentDate = currentDateInMin - bDateInMin
//                        ageMin?.text = currentDate.toString()
                    }
                }
            },
            cYear,cMonth,cDay)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
        Toast.makeText(this.requireContext(), "Today's Date is \n$cDay/${cMonth+1}/$cYear", Toast.LENGTH_SHORT).show()
    }

    private fun chooseDateForRequired(){
        //This is the current date
        var myCal = Calendar.getInstance()
        var cYear = myCal.get(Calendar.YEAR)
        var cMonth = myCal.get(Calendar.MONTH)
        var cDay = myCal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                //Take the selected date
                val selectedDate = "$day/${month+1}/$year"
                //Show the Selected date
                binding.PATIENTREQUIREDBLOOD.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val bDate = sdf.parse(selectedDate)
                bDate?.let {
                    val bDateInMin = bDate.time/60000

                    val currentDateInMillis = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDateInMillis?.let {
                        val currentDateInMin = currentDateInMillis.time/60000

//                        val currentDate = currentDateInMin - bDateInMin
//                        ageMin?.text = currentDate.toString()
                    }
                }
            },
            cYear,cMonth,cDay)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
        Toast.makeText(this.requireContext(), "Today's Date is \n$cDay/${cMonth+1}/$cYear", Toast.LENGTH_SHORT).show()
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}