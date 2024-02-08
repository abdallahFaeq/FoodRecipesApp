package com.example.foodrecipeapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("RECIPES_TABLE")
data class Recipes @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    var dishName:String?=null
)
