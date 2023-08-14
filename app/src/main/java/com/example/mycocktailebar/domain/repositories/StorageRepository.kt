package com.example.mycocktailebar.domain.repositories

import com.example.mycocktailebar.domain.models.Cocktail

interface StorageRepository {

    suspend fun addNewCocktail(cocktail: Cocktail)
    suspend fun deleteCocktail(cocktail: Cocktail)
    suspend fun getAllCocktails(): List<Cocktail>
    suspend fun getCocktail(id: Int): Cocktail
    suspend fun editCocktail(cocktail: Cocktail)
}