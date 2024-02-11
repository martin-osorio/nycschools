package com.martinosorio.a20240209_martinosorio_nycschools.api

import com.martinosorio.a20240209_martinosorio_nycschools.api.model.School
import retrofit2.Response
import retrofit2.http.GET

interface SchoolsService {

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<School>>
}