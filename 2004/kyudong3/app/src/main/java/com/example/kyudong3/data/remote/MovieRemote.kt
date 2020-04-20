package com.example.kyudong3.data.remote

import com.google.gson.annotations.SerializedName

data class MovieReceiver(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<MovieRemote>
)

data class MovieRemote(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("director") val director: String,
    @SerializedName("actor") val actor: String,
    @SerializedName("userRating") val userRating: Double
)