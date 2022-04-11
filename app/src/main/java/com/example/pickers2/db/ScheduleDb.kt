package com.example.pickers2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pickers2.BusScedule
import com.example.pickers2.daos.ScheduleDao

@Database(entities = [BusScedule::class], version = 1)
abstract class ScheduleDb : RoomDatabase() {
    abstract fun getScheduleDao():ScheduleDao

    companion object{
        private var db:ScheduleDb?=null
        fun getDb(context:Context):ScheduleDb{
            if (db==null){
                db= Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleDb::class.java,
                    "schedule_db"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}