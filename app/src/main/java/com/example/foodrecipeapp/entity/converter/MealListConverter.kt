package com.example.foodrecipeapp.entity.converter

import androidx.room.TypeConverter
import com.example.foodrecipeapp.entity.MealsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {

    @TypeConverter
    fun fromListMeal(mealItems:List<MealsItem>?):String?{
        return Gson().toJson(mealItems)
    }
    @TypeConverter
    fun toMealList(mealItemsString:String?):List<MealsItem>? =
        if (mealItemsString != null){
            Gson().fromJson(mealItemsString,object : TypeToken<List<MealsItem>?>(){}.type)
        }else null



}