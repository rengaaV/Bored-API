package com.vagner.bored.ui.bored

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vagner.bored.model.BoredLocalModel
import com.vagner.bored.model.BoredModel
import com.vagner.bored.service.local.BoredRepository
import com.vagner.bored.service.remote.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoredViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = Retrofit.init()
    private val repositoryDao = BoredRepository(application)
    val callSuccess = MutableLiveData<BoredModel>()
    val callError = MutableLiveData<String>()

    fun getType(type: String) {
        repository.getType(type).enqueue(object : Callback<BoredModel> {
            override fun onResponse(
                call: Call<BoredModel>,
                response: Response<BoredModel>
            ) {
                callSuccess.postValue(response.body())
            }

            override fun onFailure(call: Call<BoredModel>, t: Throwable) {
                callError.postValue(t.message)
            }
        })
    }

    fun getAllTypes() {
        repository.getAllTypes().enqueue(object : Callback<BoredModel> {
            override fun onResponse(
                call: Call<BoredModel>,
                response: Response<BoredModel>
            ) {
                callSuccess.postValue(response.body())
            }

            override fun onFailure(call: Call<BoredModel>, t: Throwable) {
                callError.postValue(t.message)
            }
        })
    }

    fun insert() {
        val boredLocalModel = BoredLocalModel(
            callSuccess.value!!.accessibility,
            callSuccess.value!!.activity,
            callSuccess.value!!.key,
            callSuccess.value!!.participants,
            callSuccess.value!!.price,
            callSuccess.value!!.type
        )
        repositoryDao.insert(boredLocalModel)
    }
}


