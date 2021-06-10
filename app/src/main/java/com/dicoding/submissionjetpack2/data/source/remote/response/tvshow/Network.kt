package com.dicoding.submissionjetpack2.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("origin_country")
    val originCountry: String
)
