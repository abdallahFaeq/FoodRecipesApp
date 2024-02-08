package com.example.foodrecipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.foodrecipeapp.entity.converter.MealListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MEAL_TABLE")
data class Meal @JvmOverloads constructor(
	@PrimaryKey(autoGenerate = true) var id:Int ?= 0,

	@ColumnInfo("meals")
	@Expose
	@SerializedName("meals")
	@TypeConverters(MealListConverter::class)
	val meals: List<MealsItem?> ?=null
)