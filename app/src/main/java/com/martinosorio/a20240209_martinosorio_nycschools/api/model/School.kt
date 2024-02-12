package com.martinosorio.a20240209_martinosorio_nycschools.api.model

import com.google.gson.annotations.SerializedName

data class School(
    // TODO: Most of these fields are unnecessary
    // TODO: Don't use nullables

    @SerializedName("dbn")
    var dbn: String? = null,

    @SerializedName("school_name")
    var schoolName: String? = null,

    @SerializedName("boro")
    var boro: String? = null,

    @SerializedName("overview_paragraph")
    var overviewParagraph: String? = null,

    @SerializedName("school_10th_seats")
    var school10thSeats: String? = null,

    @SerializedName("academicopportunities1")
    var academicopportunities1: String? = null,

    @SerializedName("academicopportunities2")
    var academicopportunities2: String? = null,

    @SerializedName("ell_programs")
    var ellPrograms: String? = null,

    @SerializedName("neighborhood")
    var neighborhood: String? = null,

    @SerializedName("building_code")
    var buildingCode: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("phone_number")
    var phoneNumber: String? = null,

    @SerializedName("fax_number")
    var faxNumber: String? = null,

    @SerializedName("school_email")
    var schoolEmail: String? = null,

    @SerializedName("website")
    var website: String? = null,

    @SerializedName("subway")
    var subway: String? = null,

    @SerializedName("bus")
    var bus: String? = null,

    @SerializedName("grades2018")
    var grades2018: String? = null,

    @SerializedName("finalgrades")
    var finalgrades: String? = null,

    @SerializedName("total_students")
    var totalStudents: String? = null,

    @SerializedName("extracurricular_activities")
    var extracurricularActivities: String? = null,

    @SerializedName("school_sports")
    var schoolSports: String? = null,

    @SerializedName("attendance_rate")
    var attendanceRate: String? = null,

    @SerializedName("pct_stu_enough_variety")
    var pctStuEnoughVariety: String? = null,

    @SerializedName("pct_stu_safe")
    var pctStuSafe: String? = null,

    @SerializedName("school_accessibility_description")
    var schoolAccessibilityDescription: String? = null,

    @SerializedName("directions1")
    var directions1: String? = null,

    @SerializedName("requirement1_1")
    var requirement11: String? = null,

    @SerializedName("requirement2_1")
    var requirement21: String? = null,

    @SerializedName("requirement3_1")
    var requirement31: String? = null,

    @SerializedName("requirement4_1")
    var requirement41: String? = null,

    @SerializedName("requirement5_1")
    var requirement51: String? = null,

    @SerializedName("offer_rate1")
    var offerRate1: String? = null,

    @SerializedName("program1")
    var program1: String? = null,

    @SerializedName("code1")
    var code1: String? = null,

    @SerializedName("interest1")
    var interest1: String? = null,

    @SerializedName("method1")
    var method1: String? = null,

    @SerializedName("seats9ge1")
    var seats9ge1: String? = null,

    @SerializedName("grade9gefilledflag1")
    var grade9gefilledflag1: String? = null,

    @SerializedName("grade9geapplicants1")
    var grade9geapplicants1: String? = null,

    @SerializedName("seats9swd1")
    var seats9swd1: String? = null,

    @SerializedName("grade9swdfilledflag1")
    var grade9swdfilledflag1: String? = null,

    @SerializedName("grade9swdapplicants1")
    var grade9swdapplicants1: String? = null,

    @SerializedName("seats101")
    var seats101: String? = null,

    @SerializedName("admissionspriority11")
    var admissionspriority11: String? = null,

    @SerializedName("admissionspriority21")
    var admissionspriority21: String? = null,

    @SerializedName("admissionspriority31")
    var admissionspriority31: String? = null,

    @SerializedName("grade9geapplicantsperseat1")
    var grade9geapplicantsperseat1: String? = null,

    @SerializedName("grade9swdapplicantsperseat1")
    var grade9swdapplicantsperseat1: String? = null,

    @SerializedName("primary_address_line_1")
    var primaryAddressLine1: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("zip")
    var zip: String? = null,

    @SerializedName("state_code")
    var stateCode: String? = null,

    @SerializedName("latitude")
    var latitude: String? = null,

    @SerializedName("longitude")
    var longitude: String? = null,

    @SerializedName("community_board")
    var communityBoard: String? = null,

    @SerializedName("council_district")
    var councilDistrict: String? = null,

    @SerializedName("census_tract")
    var censusTract: String? = null,

    @SerializedName("bin")
    var bin: String? = null,

    @SerializedName("bbl")
    var bbl: String? = null,

    @SerializedName("nta")
    var nta: String? = null,

    @SerializedName("borough")
    var borough: String? = null
)