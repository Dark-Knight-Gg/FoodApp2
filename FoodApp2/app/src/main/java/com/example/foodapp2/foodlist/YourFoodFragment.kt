package com.example.foodapp2.foodlist

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.*
import com.example.foodapp2.Database.Database
import com.example.foodapp2.adapter.FoodAdapterFragmentTwo
import com.example.foodapp2.model.FoodOder
import com.example.foodapp2.model.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

class YourFoodFragment : Fragment() {
    private lateinit var txtTitle: TextView
    private lateinit var foodRcv: RecyclerView
    private lateinit var adapter: FoodAdapterFragmentTwo
    private lateinit var database: Database
    private lateinit var fragTwoImgPay: ImageView

    private val listFoodOder: ArrayList<FoodOder> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_two, container, false)
        txtTitle = view.findViewById(R.id.fragTwoTxtTitle)
        fragTwoImgPay = view.findViewById(R.id.fragTwoImgPay)
        foodRcv = view.findViewById(R.id.fragTwoRecyclerView)
        adapter = FoodAdapterFragmentTwo(this, listFoodOder)
        foodRcv.layoutManager = LinearLayoutManager(activity)

        val viewModel = ViewModelProvider(requireActivity())[ItemViewModel::class.java]
        viewModel.selectedItem.observe(viewLifecycleOwner) {
            listFoodOder.add(FoodOder(it.id, it.name, it.describe, it.price, it.picture, it.count))
            adapter.notifyDataSetChanged()
        }
        foodRcv.adapter = adapter
        fragTwoImgPay.setOnClickListener {
            dialogPay()
            getHistory()
        }
        return view
    }

    fun deleteFood(position: Int) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_dialog_food)
        val dialogDeleteBtnYes: Button = dialog.findViewById(R.id.dialogDeleteBtnYes)
        val dialogDeleteBtnNo: Button = dialog.findViewById(R.id.dialogDeleteBtnNo)
        dialogDeleteBtnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialogDeleteBtnYes.setOnClickListener {
            listFoodOder.removeAt(position)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun dialogPay() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.pay_dialog)

        val dialogPayTxtMoney: TextView = dialog.findViewById(R.id.dialogPayTxtMoney)

        val dialogPayBtnOk: Button = dialog.findViewById(R.id.dialogPayBtnOk)
        var money = 0.0;
        val n = listFoodOder.size
        val k = n - 1;
        for (i in 0..k) {
            money += (listFoodOder[i].price * listFoodOder[i].count)
        }
        dialogPayTxtMoney.text = money.toString()
        dialogPayBtnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }



    @SuppressLint("SimpleDateFormat")
    private fun getHistory() {
        database = Database.getInstance(requireActivity())
        val fo = SimpleDateFormat("yyyy-MM-dd")
        val dateTime = fo.format(Date())
        val n = listFoodOder.size
        for (i in 0 until n) {
            database.queryData("INSERT INTO History VALUES(null,'$dateTime','${listFoodOder[i].name}','${listFoodOder[i].price}','${listFoodOder[i].count}','${listFoodOder[i].price * listFoodOder[i].count}')")
        }
    }
}