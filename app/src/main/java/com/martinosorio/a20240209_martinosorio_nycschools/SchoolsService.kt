package com.martinosorio.a20240209_martinosorio_nycschools

import retrofit2.Response
import retrofit2.http.GET

interface SchoolsService {

    @GET("s3k6-pzi2")
    suspend fun getSchools(): Response<String>
}