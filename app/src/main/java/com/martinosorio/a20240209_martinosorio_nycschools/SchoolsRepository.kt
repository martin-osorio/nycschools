package com.martinosorio.a20240209_martinosorio_nycschools

class SchoolsRepository(private val schoolsService: SchoolsService) {
    suspend fun getSchools() = schoolsService.getSchools()
}