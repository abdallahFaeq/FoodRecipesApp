package com.example.foodrecipeapp.interfaces

import com.example.foodrecipeapp.entity.Category
import com.example.foodrecipeapp.entity.Meal
import com.example.foodrecipeapp.entity.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IGetDataService {
    @GET("categories.php")
    fun getCategoryList():Call<Category>

    @GET("filter.php")
    fun getMealItemsList(@Query("c") category:String?):Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>
}