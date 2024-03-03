package com.example.fooddecider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.SecureRandom
import java.util.Random

class FoodViewModel: ViewModel() {
    private val _selectedFood = MutableLiveData<String>()
    val selectedFood: LiveData<String>
        get() = _selectedFood

    val foodList = arrayListOf("Chinese", "Pizza")

    fun decideOnFood() {
        val secureRandom = SecureRandom()
        val randomFood = secureRandom.nextInt(foodList.size)
        _selectedFood.value = foodList[randomFood]
    }

    fun addFood(newFood: String) {
        foodList.add(newFood)
        // Optionally, you can perform additional logic here
    }
    fun clearFoodList() {
        foodList.clear()
    }
}