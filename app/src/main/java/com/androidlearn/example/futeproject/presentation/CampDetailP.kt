package com.androidlearn.example.futeproject.presentation

import com.androidlearn.example.futeproject.activity.TabelaActivity
import com.androidlearn.example.futeproject.datasource.CampDetailDS
import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.datasource.model.time.TimeModel
import com.androidlearn.example.futeproject.interfaces.CampDetailC
import com.androidlearn.example.futeproject.interfaces.TabelaC

class CampDetailP(
    private val view: TabelaActivity,
    private val dataSource: CampDetailDS
) : CampDetailC, TabelaC {

    fun getCamp(id: Int) {
        this.view.showProgressBar()
        dataSource.findCamp(this, id)
    }

    fun getTabela(id: Int) {
        this.view.showTabelaProgressBar()
        dataSource.findTabela(this, id)
    }

    override fun onSuccess(camp: CampModel) {
        this.view.showCampDetail(camp)
    }

    override fun onNullCamp() {
        this.view.showNullCamp()
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

    override fun onSuccessTabela(tabela: MutableList<TimeModel>) {
        val tabelaList = arrayListOf<TimeModel>()
        for (time in tabela) {
            tabelaList.add(
                TimeModel(
                    time.posicao,
                    time.pontos,
                    time.time,
                    time.jogos,
                    time.vitorias,
                    time.derrotas,
                    time.empates,
                    time.gols_pro,
                    time.gols_contra,
                    time.saldo_gols
                )
            )
        }
        this.view.showTimes(tabelaList)
    }

    override fun onNullTabela() {
        this.view.showNullTabela()
    }

    override fun onErrorTabela(message: String) {
        this.view.showTabelaFailure(message)
    }

    override fun onCompleteTabela() {
        this.view.hideTabelaProgressBar()
    }
}