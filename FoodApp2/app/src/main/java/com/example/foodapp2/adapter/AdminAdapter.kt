package com.example.foodapp2.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.Client.MainActivity4
import com.example.foodapp2.R
import com.example.foodapp2.model.Food

class AdminAdapter(
    private val context: MainActivity4,
    private val listFood: ArrayList<Food>
) : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_recyclerview_admin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = listFood[position]
        val picture: ByteArray = food.picture
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.size)
        holder.adminTxtName.text = food.name
        holder.adminTxtDescribe.text = food.describe
        holder.adminTxtPrice.text = food.price.toString()
        holder.adminImg.setImageBitmap(bitmap)
        holder.adminImgDelete.setOnClickListener {
            context.diaLogDelete(food.id)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val adminImg: ImageView = view.findViewById(R.id.adminImg)
        val adminTxtName: TextView = view.findViewById(R.id.adminTxtname)
        val adminTxtDescribe: TextView = view.findViewById(R.id.adminTxtDescribe)
        val adminTxtPrice: TextView = view.findViewById(R.id.adminTxtPrice)
        val adminImgDelete: ImageView = view.findViewById(R.id.adminImgDelete)
    }

}