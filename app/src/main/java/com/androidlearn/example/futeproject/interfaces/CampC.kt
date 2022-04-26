package com.androidlearn.example.futeproject.interfaces

import com.androidlearn.example.futeproject.datasource.model.camp.CampModel

interface CampC {
    fun onSuccess(camps: MutableList<CampModel>)

    fun onError(message: String)

    fun onComplete()
}