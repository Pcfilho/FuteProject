package com.androidlearn.example.futeproject.datasource.model.time

import com.androidlearn.example.futeproject.datasource.model.time.TimeDetailsModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TimeModel(
    @SerializedName("posicao")
    val posicao: Int,
    @SerializedName("pontos")
    val pontos: Int,
    @SerializedName("time")
    val time: TimeDetailsModel,
    @SerializedName("jogos")
    val jogos: Int,
    @SerializedName("vitorias")
    val vitorias: Int,
    @SerializedName("derrotas")
    val derrotas: Int,
    @SerializedName("empates")
    val empates: Int,
    @SerializedName("gols_pro")
    val gols_pro: Int,
    @SerializedName("gols_contra")
    val gols_contra: Int,
    @SerializedName("saldo_gols")
    val saldo_gols: Int,
) : Serializable
