package com.vagner.bored.ui.my_activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vagner.bored.model.BoredModel
import com.vagner.bored.service.local.BoredRepository

class MyActivitiesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BoredRepository(application)
    val listAllBored = MutableLiveData<List<BoredModel>>()

    fun getAll() {
        listAllBored.value = repository.getAll()
    }

    fun delete(boredModel: BoredModel) {
        repository.delete(boredModel)
    }

    fun update(bored: BoredModel) {
        repository.update(bored)
    }
}