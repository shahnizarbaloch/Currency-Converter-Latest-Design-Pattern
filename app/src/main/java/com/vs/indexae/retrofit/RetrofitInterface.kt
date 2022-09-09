package com.vs.indexae.retrofit

import com.vs.indexae.model.ItemModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("latest")
    suspend fun getLatestRates(@Query("access_key") accessKey: String?) : Response<ItemModel>

}