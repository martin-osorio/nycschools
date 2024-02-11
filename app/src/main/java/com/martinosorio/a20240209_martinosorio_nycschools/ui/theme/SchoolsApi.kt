package com.martinosorio.a20240209_martinosorio_nycschools.ui.theme

import com.martinosorio.a20240209_martinosorio_nycschools.SchoolsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SchoolsApi {
    private const val BASE_URL: String = "https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val instance: SchoolsService by lazy {
        retrofit.create(SchoolsService::class.java)
    }
}