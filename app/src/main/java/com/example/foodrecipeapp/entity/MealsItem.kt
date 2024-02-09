package com.example.foodrecipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MEAL_ITEMS_TABLE")
data class MealsItem(
	@PrimaryKey(autoGenerate = true)
	var id:Int,

	@ColumnInfo("strMeal")
	@Expose
	@SerializedName("strMeal")
	val strMeal: String,

	@ColumnInfo("strMealThumb")
	@Expose
	@SerializedName("strMealThumb")
	val strMealThumb: String,

	@ColumnInfo("idMeal")
	@Expose
	@SerializedName("idMeal")
	val idMeal: String,

	@ColumnInfo("categoryName")
	val categoryName:String,
)