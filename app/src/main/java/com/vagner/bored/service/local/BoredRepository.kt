package com.vagner.bored.service.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.vagner.bored.model.BoredModel

class BoredRepository(context: Context) {

    private val boredDataBase = BoredDataBase.getDataBase(context).boredDao()

    fun insert(bored: BoredModel) {
        return boredDataBase.insert(bored)
    }

    fun getAll(): List<BoredModel> {
        return boredDataBase.getAll()
    }

    fun delete(bored: BoredModel) {
        return boredDataBase.delete(bored)

    }

    fun update(bored: BoredModel) {
        return boredDataBase.update(bored)
    }

}