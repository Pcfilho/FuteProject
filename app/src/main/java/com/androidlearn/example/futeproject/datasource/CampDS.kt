package com.androidlearn.example.futeproject.datasource

import android.util.Log
import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.datasource.remote.FutebolApi
import com.androidlearn.example.futeproject.datasource.remote.ServiceApi
import com.androidlearn.example.futeproject.interfaces.CampC
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CampDS {
    fun find(callBack: CampC) {
        FutebolApi.buildService(ServiceApi::class.java)
            .getCamps().enqueue(object : Callback<MutableList<CampModel>> {
                override fun onResponse(
                    call: Call<MutableList<CampModel>>,
                    response: Response<MutableList<CampModel>>
                ) {
                    if (response.isSuccessful) {
                        Log.e("Success", response.body().toString())
                        response.body()?.let { camps ->
                            callBack.onSuccess(camps)
                        }
                    }
                    callBack.onComplete()
                }

                override fun onFailure(call: Call<MutableList<CampModel>>, t: Throwable) {
                    t.message?.let { error ->
                        callBack.onError(error)
                    }
                }
            })
    }
}