package com.example.mycocktailebar.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image_src") val imageSrc: Int,
    val name: String,
    val description: String,
    val recipe: String,
    val ingredients: String
)
