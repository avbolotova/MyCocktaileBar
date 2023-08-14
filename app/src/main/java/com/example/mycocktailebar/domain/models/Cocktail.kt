package com.example.mycocktailebar.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cocktail(
    val id: Int = 0,
    val imageSrc: Int,
    val name: String,
    val description: String,
    val recipe: String,
    val ingredients: String
): Parcelable
