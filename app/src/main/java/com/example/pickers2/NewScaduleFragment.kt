package com.example.pickers2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pickers2.databinding.FragmentNewScaduleBinding
import com.example.pickers2.db.ScheduleDb
import com.example.pickers2.dialogs.DatePickerFragment
import com.example.pickers2.dialogs.TimePickerFragment
import com.example.pickers2.viewmodels.ScheduleViewModel

class NewScaduleFragment : Fragment() {

    private lateinit var binding:FragmentNewScaduleBinding

    private val viewmodels:ScheduleViewModel by activityViewModels()
    private var from="Dhaka"
    private var to="Dhaka"
    private var busType="Economy"
    private var selectDate=""
    private var selectTime=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentNewScaduleBinding.inflate(inflater,container,false)

        initCitySpinner()
        initBusTypeRadioGroup()
        binding.saveBtn.setOnClickListener {
            saveButtonInfo()
        }

        binding.dateBtn.setOnClickListener {
            DatePickerFragment{
                binding.showDateTv.text=it
                selectDate=it
            }.show(childFragmentManager,null)
        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment{
                selectTime=it
                binding.showTimeTv.text=it
            }.show(childFragmentManager,null)
        }

        return binding.root
    }

    private fun saveButtonInfo() {
        val busName=binding.busNameId.text.toString()
        if(from==to){
            Toast.makeText(activity, "Cant be same destination", Toast.LENGTH_SHORT).show()
        return
        }
        val scadule=BusScedule(
//            id=System.currentTimeMillis(),
            name = busName,
            from=from,
            to=to,
            departTime = selectTime,
            departDate = selectDate,
            busType=busType
        )


        //normal room save
//        ScheduleDb.getDb(requireActivity())
//            .getScheduleDao().
//            addSchedule(scadule)

        viewmodels.addSchedule(scadule)


        Log.d("TAG", "saveButtonInfo: $scadule")
        scheduleList.add(scadule)
        findNavController().navigate(R.id.action_newScaduleFragment_to_scaduleFragment2)
    }

    private fun initBusTypeRadioGroup() {
        binding.busTypeRg.setOnCheckedChangeListener { radioGroup, i ->
            val rb:RadioButton=radioGroup.findViewById(i)
            busType=rb.text.toString()
        }
    }

    private fun initCitySpinner() {
        val adapter=ArrayAdapter<String>(
            requireActivity(),android.R.layout.simple_list_item_1, cityList
        )
        binding.citySpinnerFromId.adapter=adapter
        binding.citySpinnerToId.adapter=adapter

        binding.citySpinnerFromId.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                from=p0?.getItemAtPosition(p2).toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.citySpinnerToId.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                to=p0?.getItemAtPosition(p2).toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

}
val cityList= listOf("Dhaka","Comilla","Borishal","Noakhali","Khulna","Cox's Bazar","Sylhet","Rajshahi")