package com.example.mealtalk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MealApi {
    @GET("list.php?i=list")
    fun searchRecipe(@Query("i")category: String):Call<MealData>

}