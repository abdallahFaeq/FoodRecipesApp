package com.example.foodrecipeapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("RECIPES_TABLE")
data class Recipes(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var dishName:String
)
