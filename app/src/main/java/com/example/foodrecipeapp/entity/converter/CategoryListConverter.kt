package com.example.foodrecipeapp.entity.converter

import androidx.room.TypeConverter
import com.example.foodrecipeapp.entity.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {

    @TypeConverter
    fun fromListCategory(categories:List<CategoryItems>?):String?{
        if (categories == null){
            return null
        }else{
            var gson =Gson()
            var type =object :TypeToken<CategoryItems>(){

            }.type
            return gson.toJson(categories,type)
        }
    }
    @TypeConverter
    fun toCategoryList(categoryString:String?):List<CategoryItems>?{
        if (categoryString == null){
            return null
        }else{
            var gson = Gson()
            var type = object :TypeToken<CategoryItems>(){

            }.type
            return gson.fromJson(categoryString,type)
        }
    }

}