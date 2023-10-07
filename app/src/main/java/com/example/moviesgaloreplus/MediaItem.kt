package com.example.moviesgaloreplus

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MediaItem(
    @SerialName("title")
    var title: String? = null,

    @SerialName("overview")
    var overview: String?,

    @SerialName("poster_path")
    var poster_path: String?,

    @SerialName("vote_average")
    var vote_average: Float?,

    @SerialName("backdrop_path")
    var backdrop_path: String?,

    @SerialName("id")
    var id: Int?,

    @SerialName("release_date")
    var date: String?
) : java.io.Serializable {
    var imdb_id: String = ""
    var name: String = ""
}