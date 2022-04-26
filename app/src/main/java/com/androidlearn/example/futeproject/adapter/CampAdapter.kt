package com.androidlearn.example.futeproject.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.R
import com.androidlearn.example.futeproject.interfaces.CampItemListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CampAdapter(
    val campModels: MutableList<CampModel>,
    private val context: Context
) : RecyclerView.Adapter<CampAdapter.CampViewHolder>() {

    private var listener: CampItemListener? = null

    fun setOnCampItemListener(listener: CampItemListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampViewHolder =
        CampViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_camp, parent, false),
            listener
        )

    override fun onBindViewHolder(holder: CampViewHolder, position: Int) =
        holder.bindView(campModels[position])

    override fun getItemCount(): Int = campModels.size

    fun addCamps(camps: MutableList<CampModel>) {
        this.campModels.clear()
        this.campModels.addAll(camps)
    }

    inner class CampViewHolder(itemView: View, private val listener: CampItemListener?) :
        RecyclerView.ViewHolder(itemView) {
        private val tvNome: TextView = itemView.findViewById(R.id.tvNomeCamp)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatutsCamp)
        private val tvTipo: TextView = itemView.findViewById(R.id.tvTipoCamp)
        private val progressImage: ProgressBar = itemView.findViewById(R.id.progressItemCampBarImage)
        private val progressItem: ProgressBar = itemView.findViewById(R.id.progressItemCampBar)
        private val imgLogo: ImageView = itemView.findViewById(R.id.imgCamp)
        private lateinit var campModelId: String

        fun bindView(campModel: CampModel) {
            campModelId = campModel.id


            tvNome.text = campModel.nome
            tvStatus.text = campModel.status
            tvTipo.text = campModel.tipo

            progressItem.visibility = View.GONE
            tvNome.visibility = View.VISIBLE
            tvStatus.visibility = View.VISIBLE
            tvTipo.visibility = View.VISIBLE


            val url = campModel.logo
            progressImage.visibility = View.VISIBLE
            imgLogo.visibility = View.INVISIBLE
            Glide.with(itemView)
                .load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressImage.visibility = View.GONE
                        imgLogo.visibility = View.VISIBLE
                        return false
                    }

                })
                .into(imgLogo)
        }

        init {
            itemView.setOnClickListener {
                listener?.onListItemClick(itemView, campModelId)
            }
        }

    }
}