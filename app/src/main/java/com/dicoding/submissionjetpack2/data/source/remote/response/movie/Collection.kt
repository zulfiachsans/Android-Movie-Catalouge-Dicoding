package com.dicoding.submissionjetpack2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)
