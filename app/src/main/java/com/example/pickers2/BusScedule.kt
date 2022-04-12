package com.example.pickers2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

val scheduleList= mutableListOf<BusScedule>()

@Entity(tableName = "tbl_schedule")
data class BusScedule(
  @PrimaryKey(autoGenerate = true)
  var id:Long=0,
  val name:String,
  val from:String,
  val to:String,
  @ColumnInfo(name = "dept_time")
  val departTime:String,
  @ColumnInfo(name = "dept_date")
  val departDate:String,
  @ColumnInfo(name = "bus_type")
  val busType:String,

  @ColumnInfo(name = "favorite")
  var isFav:Boolean=true
)