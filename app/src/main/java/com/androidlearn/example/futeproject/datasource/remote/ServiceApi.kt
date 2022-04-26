package com.androidlearn.example.futeproject.datasource.remote

import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.datasource.model.time.TimeModel
import com.androidlearn.example.futeproject.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ServiceApi {

    @Headers("Authorization: Bearer $API_KEY")
    @GET("campeonatos")
    fun getCamps(): Call<MutableList<CampModel>>

    @Headers("Authorization: Bearer $API_KEY")
    @GET("campeonatos/{campeonato_id}/tabela")
    fun getTabela(@Path("campeonato_id") id: Int): Call<MutableList<TimeModel>>

    @Headers("Authorization: Bearer $API_KEY")
    @GET("campeonatos/{campeonato_id}")
    fun getCamp(@Path("campeonato_id") id: Int): Call<CampModel>

}