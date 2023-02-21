package com.example.flixterapp

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BaseResponse(
    @SerialName("results")
    val results: List<Movies>?
)

@Keep
@Serializable
data class Movies (
    @SerialName("title")
    var title: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("poster_path")
    var imageUrlhalf: String? = null,
    @SerialName("release_date")
    var rdate: String? = null,
    @SerialName("vote_average")
    var voteaverage: String? = null
): java.io.Serializable {
    val mediaImageUrl = "https://image.tmdb.org/t/p/w500/${imageUrlhalf}"
}