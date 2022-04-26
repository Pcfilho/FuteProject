package com.androidlearn.example.futeproject.datasource.model.time

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TimeDetailsModel(
    @SerializedName("nome_popular")
    val nome_popular: String,
    @SerializedName("escudo")
    val escudo: String,
) : Serializable
