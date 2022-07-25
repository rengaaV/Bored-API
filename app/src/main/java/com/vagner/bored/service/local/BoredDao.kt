package com.vagner.bored.service.local

import androidx.room.*
import com.vagner.bored.model.BoredModel

@Dao
interface BoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(boredModel: BoredModel)

    @Query("SELECT * FROM bored ORDER BY id")
    fun getAll(): List<BoredModel>

    @Delete
    fun delete(boredModel: BoredModel)

    @Update
    fun update(boredModel: BoredModel)

}