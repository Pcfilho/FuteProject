package com.androidlearn.example.futeproject.presentation

import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.activity.MainActivity
import com.androidlearn.example.futeproject.datasource.CampDS
import com.androidlearn.example.futeproject.interfaces.CampC

class CampP(
    val view: MainActivity,
    val datasource: CampDS
) : CampC {

    fun requestAll() {
        this.view.showProgressBar()
        datasource.find(this)
    }

    override fun onSuccess(camps: MutableList<CampModel>) {
        val campsList = arrayListOf<CampModel>()
        for (camp in camps) {
            campsList.add(CampModel(camp.id, camp.nome, camp.status, camp.tipo, camp.logo))
        }
        this.view.showCamps(campsList)
    }


    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}