package com.example.foodrecipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) var id:Int?=0,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")

    var categorieitems: List<CategoryItems>? = null
)