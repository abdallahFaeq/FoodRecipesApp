package com.example.foodrecipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipeapp.dao.RecipesDao
import com.example.foodrecipeapp.entity.Category
import com.example.foodrecipeapp.entity.CategoryItems
import com.example.foodrecipeapp.entity.Meal
import com.example.foodrecipeapp.entity.MealsItem
import com.example.foodrecipeapp.entity.Recipes
import com.example.foodrecipeapp.entity.converter.CategoryListConverter
import com.example.foodrecipeapp.entity.converter.MealListConverter

@Database(entities = [Recipes::class,Category::class,CategoryItems::class,
                     Meal::class,MealsItem::class], version = 4)
@TypeConverters(CategoryListConverter::class,MealListConverter::class)
abstract class RecipesDatabase:RoomDatabase() {
    abstract fun getRecipesDao():RecipesDao

    companion object{

        @Volatile
        private var recipesDBInstance:RecipesDatabase?=null
        @Synchronized
        fun getRecipesDBInstance(context: Context):RecipesDatabase{
            if (recipesDBInstance==null){
                recipesDBInstance = buildDB(context)
            }
            return recipesDBInstance!!
        }

        private fun buildDB(context:Context): RecipesDatabase? {
            return Room.databaseBuilder(context,RecipesDatabase::class.java,"Recipes_DB")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}