package com.dicoding.submissionjetpack3.data.source.remote.response.movie


import com.google.gson.annotations.SerializedName

data class MovieResponse(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<Movie>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)