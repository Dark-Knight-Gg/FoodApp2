package com.example.foodapp2.Client

import android.content.Intent

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.Admin.MainActivity3
import com.example.foodapp2.Database.Database
import com.example.foodapp2.R
import com.example.foodapp2.adapter.HistoryAdapter
import com.example.foodapp2.model.History

class MainActivity6 : AppCompatActivity() {
    private lateinit var database: Database
    private val listHistory = ArrayList<History>()
    private lateinit var adapter: HistoryAdapter
    private lateinit var atv6ImgBack: ImageView
    private lateinit var atv6RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        getData()
        bindView()
        initListener()
        adapter = HistoryAdapter(listHistory)
        atv6RecyclerView.layoutManager = LinearLayoutManager(this)
        atv6RecyclerView.adapter = adapter

    }

    private fun bindView() {
        atv6ImgBack = findViewById(R.id.avt6ImgBack)
        atv6RecyclerView = findViewById(R.id.avt6RecyclerView)
    }

    private fun initListener() {
        atv6ImgBack.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        database = Database.getInstance(this)
        listHistory.clear()
        listHistory.addAll(database.getHistory())
    }
}