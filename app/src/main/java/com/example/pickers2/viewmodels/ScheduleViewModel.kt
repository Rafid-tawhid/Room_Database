package com.example.pickers2.viewmodels

import android.app.Application
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pickers2.BusScedule
import com.example.pickers2.db.ScheduleDb
import com.example.pickers2.repos.ScheduleLocalRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application):AndroidViewModel(application) {
    private lateinit var repository: ScheduleLocalRepository

    init {
        val dao=ScheduleDb.getDb(application).getScheduleDao()
        repository= ScheduleLocalRepository(dao)
    }

    fun addSchedule(busScedule: BusScedule){
        viewModelScope.launch {
            repository.addSchedule(busScedule)
        }
    }
    fun updateSchedule(busScedule: BusScedule){
        viewModelScope.launch {
            repository.updateSchedule(busScedule)
        }
    }
    fun deleteSchedule(busScedule: BusScedule){
        viewModelScope.launch {
            repository.deleteSchedule(busScedule)
        }
    }
    fun getAllSchedules():LiveData<List<BusScedule>> = repository.getAllSchedules()

    fun getSchedulesById(id:Long):LiveData<BusScedule> = repository.getSchedulesById(id)

}