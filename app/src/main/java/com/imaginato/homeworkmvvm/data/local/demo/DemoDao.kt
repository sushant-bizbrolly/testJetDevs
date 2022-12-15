package com.imaginato.homeworkmvvm.data.local.demo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DemoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDemo(demo: Demo)

    @Query("DELETE FROM demo")
    fun deleteAll()
}