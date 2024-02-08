package com.example.foodrecipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MEAL_ITEMS_TABLE")
data class MealsItem @JvmOverloads constructor(
	@PrimaryKey(autoGenerate = true)
	var id:Int? = 0,

	@ColumnInfo("strMeal")
	@Expose
	@SerializedName("strMeal")
	val strMeal: String? =null,

	@ColumnInfo("strMealThumb")
	@Expose
	@SerializedName("strMealThumb")
	val strMealThumb: String?=null,

	@ColumnInfo("idMeal")
	@Expose
	@SerializedName("idMeal")
	val idMeal: String? =null,

	@ColumnInfo("categoryName")
	val categoryName:String?=null,
){
	constructor():this(0,"","","","")
}