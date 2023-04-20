package com.example.mealtalk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewMode:ViewModel() {
    private val _listMeal = MutableLiveData<List<Meal>>()
    val listMeal :LiveData<List<Meal>> = _listMeal
    var MealItem =MutableLiveData<List<Meal>>()

    fun getMyData() {
            RetrofitInstance.api.searchRecipe("list").enqueue(object :Callback<MealData>{
                override fun onResponse(call: Call<MealData>, response: Response<MealData>) {
             if(response.body() !=null){

                 _listMeal.value = response.body()!!.meals
             }
                }
                override fun onFailure(call: Call<MealData>, t: Throwable) {
                    Log.d("ger","wkfw"+t.message)
                }

            })
    }
    fun observeLiveData():LiveData<List<Meal>>{
        return MealItem
    }}



