package com.martinosorio.a20240209_martinosorio_nycschools.api.model

import com.google.gson.annotations.SerializedName

data class Score(
    /*
        This model contains objects for all fields seen in the response of the API.
        Some of these objects could be excluded if they are known to not be needed.

        Given more time and technical guidance or documentation,
        these objects would not be nullable, and their types would be more specific.
     */

    @SerializedName("dbn")
    var dbn: String? = null,

    @SerializedName("school_name")
    var schoolName: String? = null,

    @SerializedName("num_of_sat_test_takers")
    var numOfSatTestTakers: String? = null,

    @SerializedName("sat_critical_reading_avg_score")
    var readingAvgScore: String? = null,

    @SerializedName("sat_math_avg_score")
    var mathAvgScore: String? = null,

    @SerializedName("sat_writing_avg_score")
    var writingAvgScore: String? = null,
)