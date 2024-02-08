package com.example.foodrecipeapp.retrofitclients

import com.example.foodrecipeapp.interfaces.IGetDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {
    companion object{
        private var retrofitInstance : Retrofit ?= null
        private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
        private fun getRetrofitInstance():Retrofit{
            if (retrofitInstance == null){
                retrofitInstance = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofitInstance!!
        }
        val categoryService = getRetrofitInstance().create(IGetDataService::class.java)
    }
}