package com.vagner.bored.service.remote

import com.vagner.bored.model.BoredModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("api/activity/")
    fun getType(@Query("type") type: String): Call<BoredModel>

    @GET("api/activity/")
    fun getAllTypes(): Call<BoredModel>
}