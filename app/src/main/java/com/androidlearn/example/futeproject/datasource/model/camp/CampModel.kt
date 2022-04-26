package com.androidlearn.example.futeproject.datasource.model.camp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CampModel(
    @SerializedName("campeonato_id")
    val id: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("logo")
    val logo: String
) : Serializable