package com.androidlearn.example.futeproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.androidlearn.example.futeproject.R
import com.androidlearn.example.futeproject.datasource.model.time.TimeModel
import com.androidlearn.example.futeproject.util.loadSvgOrOther
import java.util.ArrayList

class TabelaAdapter(
    val timeModels: MutableList<TimeModel>,
    private val context: Context
) : RecyclerView.Adapter<TabelaAdapter.TimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder =
        TimeViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_time, parent, false)
        )

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) =
        holder.bindView(timeModels[position])

    override fun getItemCount(): Int = timeModels.size

    fun addTimes(tabelaList: ArrayList<TimeModel>) {
        this.timeModels.clear()
        this.timeModels.addAll(tabelaList)
    }

    inner class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutCard = itemView.findViewById<ConstraintLayout>(R.id.item_card_layout)
        private val tvNome: TextView = itemView.findViewById(R.id.tvNomeTime)
        private val tvJogos: TextView = itemView.findViewById(R.id.tvJogos)
        private val tvPontos: TextView = itemView.findViewById(R.id.tvPontos)
        private val tvVitorias: TextView = itemView.findViewById(R.id.tvVitorias)
        private val tvDerrotas: TextView = itemView.findViewById(R.id.tvDerrotas)
        private val tvEmpates: TextView = itemView.findViewById(R.id.tvEmpates)
        private val tvGolsPro: TextView = itemView.findViewById(R.id.tvGolsPro)
        private val tvGolContra: TextView = itemView.findViewById(R.id.tvGolContra)
        private val tvSaldo: TextView = itemView.findViewById(R.id.tvSaldo)
        private val tvPosicao: TextView = itemView.findViewById(R.id.tvPosicao)
        private val imgLogo: ImageView = itemView.findViewById(R.id.imgTime)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bindView(timeModel: TimeModel) {
            if (timeModel.posicao <= 3) {
                layoutCard.setBackgroundColor(Color.parseColor("#00509D"))
                tvNome.setTextColor(Color.WHITE)
                tvPontos.setTextColor(Color.WHITE)
                tvJogos.setTextColor(Color.WHITE)
                tvVitorias.setTextColor(Color.WHITE)
                tvDerrotas.setTextColor(Color.WHITE)
                tvEmpates.setTextColor(Color.WHITE)
                tvGolsPro.setTextColor(Color.WHITE)
                tvGolContra.setTextColor(Color.WHITE)
                tvSaldo.setTextColor(Color.WHITE)
                tvPosicao.setTextColor(Color.WHITE)
            } else {
                layoutCard.setBackgroundColor(Color.WHITE)
                tvNome.setTextColor(Color.BLACK)
                tvPontos.setTextColor(Color.BLACK)
                tvJogos.setTextColor(Color.BLACK)
                tvVitorias.setTextColor(Color.BLACK)
                tvDerrotas.setTextColor(Color.BLACK)
                tvEmpates.setTextColor(Color.BLACK)
                tvGolsPro.setTextColor(Color.BLACK)
                tvGolContra.setTextColor(Color.BLACK)
                tvSaldo.setTextColor(Color.BLACK)
                tvPosicao.setTextColor(Color.BLACK)
            }
            tvNome.text = timeModel.time.nome_popular
            tvJogos.text = "Jogos: ${timeModel.jogos}"
            tvPontos.text = "Pontos: ${timeModel.pontos}"
            tvVitorias.text = "Vitorias: ${timeModel.vitorias}"
            tvDerrotas.text = "Derrotas: ${timeModel.derrotas}"
            tvEmpates.text = "Empates: ${timeModel.empates}"
            tvGolsPro.text = "Gols Pro: ${timeModel.gols_pro}"
            tvGolContra.text = "Gols Contra: ${timeModel.gols_contra}"
            tvSaldo.text = "Saldo de Gols: ${timeModel.saldo_gols}"
            tvPosicao.text = "Posição #${timeModel.posicao}"
            imgLogo.loadSvgOrOther(timeModel.time.escudo)
            progressBar.visibility = View.GONE
            tvNome.visibility = View.VISIBLE
            tvJogos.visibility = View.VISIBLE
            tvPontos.visibility = View.VISIBLE
            tvVitorias.visibility = View.VISIBLE
            tvDerrotas.visibility = View.VISIBLE
            tvEmpates.visibility = View.VISIBLE
            tvGolsPro.visibility = View.VISIBLE
            tvGolContra.visibility = View.VISIBLE
            tvSaldo.visibility = View.VISIBLE
            tvPosicao.visibility = View.VISIBLE
        }
    }
}