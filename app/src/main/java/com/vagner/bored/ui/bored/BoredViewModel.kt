package com.vagner.bored.ui.bored

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vagner.bored.R
import com.vagner.bored.model.BoredModel
import com.vagner.bored.service.local.BoredRepository
import com.vagner.bored.service.remote.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BoredViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Retrofit.init()
    private val repositoryDao = BoredRepository(application)
    val callSuccess = MutableLiveData<BoredModel>()
    val callError = MutableLiveData<String>()

    fun getAllTypes(type: String) {
        repository.getAllTypes(type).enqueue(object : Callback<BoredModel> {
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
        val date = Calendar.getInstance().time
        val data = date.toString()

        callSuccess.value!!.progress = getApplication<Application>().getString(R.string.progresso_bored_view_model)
        callSuccess.value!!.data = data
        callSuccess.value!!.end = ""

        repositoryDao.insert(callSuccess.value!!)

    }
}


