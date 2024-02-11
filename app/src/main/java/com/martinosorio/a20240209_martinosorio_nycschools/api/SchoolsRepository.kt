package com.martinosorio.a20240209_martinosorio_nycschools.api

class SchoolsRepository(private val schoolsService: SchoolsService) {
    suspend fun getSchools() = schoolsService.getSchools()
}