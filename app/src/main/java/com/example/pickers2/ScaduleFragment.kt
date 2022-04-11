package com.example.pickers2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pickers2.databinding.FragmentScaduleBinding
import com.example.pickers2.db.ScheduleDb
import com.example.pickers2.viewmodels.HomeViewModel
import com.example.pickers2.viewmodels.ScheduleViewModel

class ScaduleFragment : Fragment() {
    private lateinit var binding:FragmentScaduleBinding
    private val viewModel:HomeViewModel by viewModels()
    private val viewmodels: ScheduleViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentScaduleBinding.inflate(inflater,container,false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_scaduleFragment2_to_newScaduleFragment)
        }
        val adapter=ScheduleAdapter()

        binding.rv.layoutManager= LinearLayoutManager(activity)
        binding.rv.adapter=adapter
        binding.rv.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0){
                    binding.floatingActionButton.hide()
                }
                if(dy<0){
                    binding.floatingActionButton.show()
                }
            }
        })
       //adapter.submitList(scheduleList)
       // adapter.submitList(ScheduleDb.getDb(requireActivity()).getScheduleDao().getAllSchedule())

       // adapter.submitList(viewmodels.getAllSchedules())
        viewmodels.getAllSchedules().observe(viewLifecycleOwner){
            scheduleList->
            adapter.submitList(scheduleList)
        }
        return binding.root
    }


}