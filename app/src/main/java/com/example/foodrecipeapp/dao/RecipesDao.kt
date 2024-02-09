package com.example.foodrecipeapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipeapp.entity.Category
import com.example.foodrecipeapp.entity.CategoryItems
import com.example.foodrecipeapp.entity.MealsItem

@Dao
interface RecipesDao {
//    @get:Query("select * from Category order by id desc")
//    val allRecipes:List<Category>
    @Query("select * from CategoryItems order by id desc")
    suspend fun getAllCategories():List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems:CategoryItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealItems:MealsItem)

    @Query("DELETE FROM CategoryItems")
    suspend fun clearDB()

    @Query("delete from MEAL_ITEMS_TABLE")
    suspend fun clearMealItemDB()
    @Query("select * from MEAL_ITEMS_TABLE where categoryName like :categoryName order by id desc")
    suspend fun getSpecificMealsList(categoryName:String):List<MealsItem>
}