package com.androidlearn.example.futeproject.datasource

import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.datasource.model.time.TimeModel
import com.androidlearn.example.futeproject.datasource.remote.FutebolApi
import com.androidlearn.example.futeproject.datasource.remote.ServiceApi
import com.androidlearn.example.futeproject.interfaces.CampDetailC
import com.androidlearn.example.futeproject.interfaces.TabelaC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CampDetailDS {
    fun findCamp(callback: CampDetailC, id: Int) {
        FutebolApi.buildService(ServiceApi::class.java)
            .getCamp(id).enqueue(object : Callback<CampModel> {
                override fun onResponse(call: Call<CampModel>, response: Response<CampModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let { camp ->
                            callback.onSuccess(camp)
                        }
                    } else {
                        callback.onNullCamp()
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<CampModel>, t: Throwable) {
                    t.message?.let { error ->
                        callback.onError(error)
                    }
                }

            })
    }

    fun findTabela(callback: TabelaC, id: Int) {
        FutebolApi.buildService(ServiceApi::class.java)
            .getTabela(id).enqueue(object : Callback<MutableList<TimeModel>> {
                override fun onResponse(
                    call: Call<MutableList<TimeModel>>,
                    response: Response<MutableList<TimeModel>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { tabela ->
                            callback.onSuccessTabela(tabela)
                        }
                    } else {
                        callback.onNullTabela()
                    }
                    callback.onCompleteTabela()
                }

                override fun onFailure(call: Call<MutableList<TimeModel>>, t: Throwable) {
                    t.message?.let { error ->
                        callback.onErrorTabela(error)
                    }
                }

            })
    }
}