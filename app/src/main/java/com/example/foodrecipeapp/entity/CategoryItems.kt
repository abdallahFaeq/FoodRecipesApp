package com.example.foodrecipeapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CategoryItems")
data class CategoryItems @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=0,

    @ColumnInfo(name = "idcategory")
    @Expose
    @SerializedName("idCategory")
    val idcategory: String?=null,

    @ColumnInfo(name = "strcategory")
    @Expose
    @SerializedName("strCategory")
    val strcategory: String?=null,

    @ColumnInfo(name = "strcategorythumb")
    @Expose
    @SerializedName("strCategoryThumb")
    val strcategorythumb: String?=null,

    @ColumnInfo(name = "strcategorydescription")
    @Expose
    @SerializedName("strCategoryDescription")
    val strcategorydescription: String?=null
)