package com.example.foodapp2.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.R
import com.example.foodapp2.foodlist.FoodListFragment
import com.example.foodapp2.model.Food
import com.example.foodapp2.model.FoodOder

class FoodAdapter(
    private var context: FoodListFragment,
    private var listFood: ArrayList<Food>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recyclerviewfood, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = listFood[position]
        val picture: ByteArray = food.picture
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.size)
        holder.customTxtName.text = food.name
        holder.customTxtDescribe.text = food.describe
        holder.customTxtPrice.text = food.price.toString()
        holder.customImg.setImageBitmap(bitmap)
        holder.customBtn.setOnClickListener {
            val countS = holder.customEdtCount.text.toString()
            val countI = countS.toInt()
            val foodOder =
                FoodOder(food.id, food.name, food.describe, food.price, food.picture, countI)
            context.sendData(foodOder)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var customImg: ImageView = view.findViewById(R.id.customImg)
        var customTxtName: TextView = view.findViewById(R.id.customTxtName)
        var customTxtDescribe: TextView = view.findViewById(R.id.customTxtDescribe)
        var customTxtPrice: TextView = view.findViewById(R.id.customTxtPrice)
        var customEdtCount: EditText = view.findViewById(R.id.customEdtCount)
        var customBtn: Button = view.findViewById(R.id.customBtn)
    }
}