package com.androidlearn.example.futeproject.interfaces

import com.androidlearn.example.futeproject.datasource.model.camp.CampModel

interface CampDetailC {
    fun onSuccess(camp: CampModel)

    fun onNullCamp()

    fun onError(message: String)

    fun onComplete()
}