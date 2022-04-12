package com.example.pickers2.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pickers2.BusScedule

@Dao
interface ScheduleDao {

    @Insert
    suspend  fun addSchedule(busScedule: BusScedule)

    @Update
    suspend fun updateSchedule(busScedule: BusScedule)

    @Delete
    suspend  fun deleteSchedule(busScedule: BusScedule)

    @Query("select *from tbl_schedule")
    fun getAllSchedule():LiveData<List<BusScedule>>

    @Query("select *from tbl_schedule where id=:id")
    fun getScheduleById(id:Long):LiveData<BusScedule>
}