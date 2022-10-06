package com.example.foodapp2.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp2.model.FoodOder

class ItemViewModel : ViewModel() {

    val selectedItem: MutableLiveData<FoodOder> = MutableLiveData<FoodOder>()

    fun sendFood(foodOder: FoodOder) {
        selectedItem.value = foodOder
    }


}