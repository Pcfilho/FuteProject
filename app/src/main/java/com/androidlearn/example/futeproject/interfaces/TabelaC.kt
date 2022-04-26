package com.androidlearn.example.futeproject.interfaces

import com.androidlearn.example.futeproject.datasource.model.time.TimeModel

interface TabelaC {
    fun onSuccessTabela(tabela: MutableList<TimeModel>)

    fun onNullTabela()

    fun onErrorTabela(message: String)

    fun onCompleteTabela()
}