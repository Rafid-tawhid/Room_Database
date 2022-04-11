package com.example.pickers2.repos

import androidx.lifecycle.LiveData
import com.example.pickers2.BusScedule
import com.example.pickers2.daos.ScheduleDao

class ScheduleLocalRepository(private val dao:ScheduleDao) {
   suspend fun addSchedule(busScedule: BusScedule)=dao.addSchedule(busScedule)
   suspend fun updateSchedule(busScedule: BusScedule)=dao.updateSchedule(busScedule)
   suspend fun deleteSchedule(busScedule: BusScedule)=dao.deleteSchedule(busScedule)
    fun getAllSchedules():LiveData<List<BusScedule>> = dao.getAllSchedule()
}