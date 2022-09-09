package com.vs.indexae.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    val api: RetrofitInterface
        get() = retrofit.create(RetrofitInterface::class.java)

    companion object {
        private const val BASE_URL = "http://data.fixer.io/api/"
        //BuildConfig.BASE_URL;
        private var retrofitClient: RetrofitClient? = null
        @JvmStatic
        @get:Synchronized
        val instance: RetrofitClient?
            get() {
                if (retrofitClient == null) {
                    retrofitClient = RetrofitClient()
                }
                return retrofitClient
            }
    }
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
}