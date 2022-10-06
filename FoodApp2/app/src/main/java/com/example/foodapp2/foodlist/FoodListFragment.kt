package com.example.foodapp2.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.*
import com.example.foodapp2.Database.Database
import com.example.foodapp2.adapter.FoodAdapter
import com.example.foodapp2.model.Food
import com.example.foodapp2.model.FoodOder
import com.example.foodapp2.model.ItemViewModel

class FoodListFragment : Fragment() {
    private var listFood = ArrayList<Food>()
    private lateinit var foodListRv: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var viewModel: ItemViewModel

    private val database: Database by lazy { Database.getInstance(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_one, container, false)
        foodListRv = view.findViewById(R.id.fragOneRecyclerView)
        foodAdapter = FoodAdapter(this, listFood)
        foodListRv.layoutManager = LinearLayoutManager(activity)
        foodListRv.adapter = foodAdapter
        getData()
        return view
    }

    private fun getData() {
        listFood.clear()
        listFood.addAll(database.getFoodList())
    }

    fun sendData(foodOder: FoodOder) {
        viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.sendFood(foodOder)
    }
}