package com.dicoding.submissionjetpack3.data.source.remote.response.tv


import com.google.gson.annotations.SerializedName

data class CountryProduction(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("name")
        val name: String
)