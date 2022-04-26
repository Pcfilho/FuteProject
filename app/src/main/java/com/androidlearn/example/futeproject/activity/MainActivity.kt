package com.androidlearn.example.futeproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidlearn.example.futeproject.datasource.model.camp.CampModel
import com.androidlearn.example.futeproject.adapter.CampAdapter
import com.androidlearn.example.futeproject.databinding.ActivityMainBinding
import com.androidlearn.example.futeproject.datasource.CampDS
import com.androidlearn.example.futeproject.interfaces.CampItemListener
import com.androidlearn.example.futeproject.presentation.CampP

class MainActivity : AppCompatActivity(), CampItemListener {
    private lateinit var binding: ActivityMainBinding

    private val mainAdapter by lazy {
        CampAdapter(camps(), this)
    }
    private val recycle by lazy {
        binding.rvCamps
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configRecycle()

        val campSource = CampDS()
        val presenter = CampP(this, campSource)

        presenter.requestAll()
    }


    private fun configRecycle() {
        with(recycle) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            mainAdapter.setOnCampItemListener(this@MainActivity)
        }
    }

    private fun camps(): MutableList<CampModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun showCamps(camps: MutableList<CampModel>) {
        with(mainAdapter) {
            addCamps(camps)
            notifyDataSetChanged()
        }
    }

    fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.e("ERROR", message)
    }

    fun showProgressBar() = with(binding) {
        progressCircular2.visibility = View.VISIBLE
    }

    fun hideProgressBar() = with(binding) {
        progressCircular2.visibility = View.INVISIBLE
    }

    override fun onListItemClick(view: View, id: String) {
        val intent = Intent(applicationContext, TabelaActivity::class.java)
        intent.putExtra("campeonato_id", id)
        startActivity(intent)
    }


}