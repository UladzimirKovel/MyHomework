package com.example.myhomework.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponce(
    @SerializedName("info") val info: Characters,
    @SerializedName("data") val data: List<Info>
) : Parcelable

@Parcelize
data class OneApiResponce(
    @SerializedName("info") val info: Characters,
    @SerializedName("data") val data: Info
) : Parcelable


@Parcelize
data class Characters(
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("previousPage") val previousPage: String?,
    @SerializedName("nextPage") val nextPage: String?
) : Parcelable

@Parcelize
data class Info(
    @SerializedName("_id") val _id: Int,
    @SerializedName("films") val films: List<String>,
    @SerializedName("shortFilms") val shortFilms: List<String>,
    @SerializedName("tvShows") val tvShows: List<String>,
    @SerializedName("videoGames") val videoGames: List<String>,
    @SerializedName("parkAttractions") val parkAttractions: List<String>,
    @SerializedName("allies") val allies: List<String>,
    @SerializedName("enemies") val enemies: List<String>,
    @SerializedName("sourceUrl") val sourceUrl: String?,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("url") val url: String?,
    @SerializedName("__v") val __v: Int
) : Parcelable

