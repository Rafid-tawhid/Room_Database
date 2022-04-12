package com.example.pickers2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pickers2.databinding.FragmentScaduleBinding
import com.example.pickers2.dialogs.CustomAlertDialog
import com.example.pickers2.viewmodels.HomeViewModel
import com.example.pickers2.viewmodels.ScheduleViewModel
import com.google.android.material.snackbar.Snackbar

class ScaduleFragment : Fragment() {
    private lateinit var binding:FragmentScaduleBinding
  //  private val viewModel:HomeViewModel by viewModels()
    private val viewmodels: ScheduleViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentScaduleBinding.inflate(inflater,container,false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_scaduleFragment2_to_newScaduleFragment)
        }
        //::onClickResponse,::onFavChange
        val adapter=ScheduleAdapter(::onFavChange,::onMenuItemClicked)

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




    fun onClickResponse(schedule: BusScedule){

        Toast.makeText(requireActivity(), ""+schedule.name, Toast.LENGTH_SHORT).show()
    }

    fun onFavChange(schedule: BusScedule){
        Toast.makeText(requireActivity(), schedule.name+" Added to Favorite", Toast.LENGTH_SHORT).show()
    }

    private fun onMenuItemClicked(schedule: BusScedule,action: RowAction){

        when(action){
            RowAction.EDIT->{
                val bundle = bundleOf("id" to schedule.id)
                findNavController().navigate(R.id.action_scaduleFragment2_to_newScaduleFragment,bundle)
            }
            RowAction.DELETE->{
              //  viewmodels.deleteSchedule(schedule)
                CustomAlertDialog(
                    icon = R.drawable.ic_baseline_delete_24,
                    "Delete ${schedule.name} ?",
                    "Are you sure to delete this item?",
                    "Yes",
                    "No"
                ){
                    viewmodels.deleteSchedule(schedule)
                 val snackbar= Snackbar.make(binding.rv,"Deleted",Snackbar.LENGTH_LONG)
                  snackbar.duration=5000
                   snackbar.setAction("Undo"){

                       viewmodels.addSchedule(schedule)
                   }
                    snackbar.show()

                }.show(childFragmentManager,null)
            }
        }
    }


}