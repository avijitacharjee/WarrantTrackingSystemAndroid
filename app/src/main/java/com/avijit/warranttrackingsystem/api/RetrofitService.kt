package com.avijit.warranttrackingsystem.api

import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    var okHttpClient: OkHttpClient? = null
    private fun initialize() {
        okHttpClient = Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://warrant.susmoycse.com/server/api/")
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }

    init {
        initialize()
    }
}