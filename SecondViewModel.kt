package com.example.mealtalk

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navArgument
import com.example.mealtalk.databinding.FragmentSecondBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondViewModel:ViewModel() {

    private val _recipeDescription = MutableLiveData<List<Meal>>()
    val recipeDescription: LiveData<List<Meal>> = _recipeDescription
    var MealItem =MutableLiveData<List<Meal>>()

    fun receiveData() {

        RetrofitInstance.api.searchRecipe("list").enqueue(object : Callback<MealData?> {
            override fun onResponse(call: Call<MealData?>, response: Response<MealData?>) {
                if (response.body() != null) {
                    _recipeDescription.value= response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealData?>, t: Throwable) {
                Log.d("ger","wkfw"+t.message)
            }
        })
    }
    fun observeLiveData():LiveData<List<Meal>>{
        return MealItem
    }
}