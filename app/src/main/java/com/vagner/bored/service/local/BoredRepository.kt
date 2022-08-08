package com.vagner.bored.service.local

import android.content.Context
import com.vagner.bored.model.BoredLocalModel

class BoredRepository(context: Context) {

    private val boredDataBase = BoredDataBase.getDataBase(context).boredDao()

    fun insert(bored: BoredLocalModel) {
        return boredDataBase.insert(bored)
    }

    fun getAll(): List<BoredLocalModel> {
        return boredDataBase.getAll()
    }

    fun delete(bored: BoredLocalModel) {
        return boredDataBase.delete(bored)

    }

    fun update(bored: BoredLocalModel) {
        return boredDataBase.update(bored)
    }

}