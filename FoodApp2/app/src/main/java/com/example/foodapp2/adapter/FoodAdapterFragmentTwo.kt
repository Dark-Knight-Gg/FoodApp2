package com.example.foodapp2.adapter


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp2.R
import com.example.foodapp2.foodlist.YourFoodFragment
import com.example.foodapp2.model.FoodOder

class FoodAdapterFragmentTwo(
    private var context: YourFoodFragment,
    private var listFoodOder: List<FoodOder>
) : RecyclerView.Adapter<FoodAdapterFragmentTwo.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recyclerviewfood2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = listFoodOder[position]
        val picture: ByteArray = food.picture
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.size)
        holder.frag2TxtName.text = food.name
        holder.frag2TxtDescribe.text = food.describe
        holder.frag2TxtPrice.text = food.price.toString()
        holder.frag2Img.setImageBitmap(bitmap)
        holder.frag2TxtCount.text = food.count.toString()
        holder.frag2ImgDelete.setOnClickListener {
            context.deleteFood(position)
        }
    }

    override fun getItemCount(): Int = listFoodOder.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var frag2Img: ImageView = view.findViewById(R.id.frag2Img)
        var frag2TxtName: TextView = view.findViewById(R.id.frag2TxtName)
        var frag2TxtDescribe: TextView = view.findViewById(R.id.frag2TxtDescribe)
        var frag2TxtPrice: TextView = view.findViewById(R.id.frag2TxtPrice)
        var frag2TxtCount: TextView = view.findViewById(R.id.frag2TxtCount)
        var frag2ImgDelete: ImageView = view.findViewById(R.id.frag2ImgDelete)
    }
}