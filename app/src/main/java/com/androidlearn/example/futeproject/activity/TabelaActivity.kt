package com.androidlearn.example.futeproject.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidlearn.example.futeproject.adapter.CampAdapter
import com.androidlearn.example.futeproject.adapter.TabelaAdapter
import com.androidlearn.example.futeproject.databinding.DetailsCampBinding
import com.androidlearn.example.futeproject.datasource.CampDetailDS
import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.datasource.model.time.TimeModel
import com.androidlearn.example.futeproject.presentation.CampDetailP
import com.bumptech.glide.Glide
import java.util.ArrayList

class TabelaActivity : AppCompatActivity() {
    private lateinit var binding: DetailsCampBinding

    private lateinit var id: String

    private val tabelaAdapter by lazy {
        TabelaAdapter(times(), this)
    }

    private val recycle by lazy {
        binding.rvTimes
    }

    companion object {
        const val KEY_ID = "campeonato_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsCampBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCampSend()
        configRecycle()

        val campDetailDS = CampDetailDS()
        val presenter = CampDetailP(this, campDetailDS)

        presenter.getCamp(id.toInt())

        presenter.getTabela(id.toInt())


    }


    private fun configRecycle() {
        with(recycle) {
            adapter = tabelaAdapter
            layoutManager = LinearLayoutManager(this@TabelaActivity)
        }
    }

    private fun getCampSend() {
        intent.extras?.let {
            id = it.getString(KEY_ID)!!
        }
    }

    private fun times(): MutableList<TimeModel> = arrayListOf()

    fun showProgressBar() = with(binding) {
        progressCampTopCard.visibility = View.VISIBLE
    }

    fun hideProgressBar() = with(binding) {
        progressCampTopCard.visibility = View.GONE
    }

    fun showTabelaProgressBar() = with(binding) {
        progressBarDetailsTabela.visibility = View.VISIBLE
    }

    fun hideTabelaProgressBar() = with(binding) {
        progressBarDetailsTabela.visibility = View.GONE
    }

    fun showCampDetail(camp: CampModel) = with(binding) {
        tvNomeCamp2.text = camp.nome
        tvStatutsCamp2.text = camp.status
        tvTipoCamp2.text = camp.tipo
        Glide.with(this@TabelaActivity)
            .load(camp.logo)
            .into(imgCampTabela)
        tvNomeCamp2.visibility = View.VISIBLE
        tvStatutsCamp2.visibility = View.VISIBLE
        tvTipoCamp2.visibility = View.VISIBLE
        tvStringTipo.visibility = View.VISIBLE
        tvStringStatus.visibility = View.VISIBLE
        imgCampTabela.visibility = View.VISIBLE

    }

    fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showTabelaFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showTimes(tabelaList: ArrayList<TimeModel>) {
        with(tabelaAdapter) {
            addTimes(tabelaList)
            notifyDataSetChanged()
        }
    }

    fun showNullTabela() {
        with(binding) {
            tvErrorTabela.visibility = View.VISIBLE
        }
    }

    fun showNullCamp() {
        with(binding) {
            tvErrorDetailsCamp.visibility = View.VISIBLE
        }
    }

}