package com.vagner.bored.service.local

import androidx.room.*
import com.vagner.bored.model.BoredLocalModel

@Dao
interface BoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(boredModel: BoredLocalModel)

    @Query("SELECT * FROM bored ORDER BY id")
    fun getAll(): List<BoredLocalModel>

    @Delete
    fun delete(boredModel: BoredLocalModel)

    @Update
    fun update(boredModel: BoredLocalModel)

}