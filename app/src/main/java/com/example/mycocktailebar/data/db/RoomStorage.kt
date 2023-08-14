package com.example.mycocktailebar.data.db

import com.example.mycocktailebar.data.db.dto.CocktailDto

interface RoomStorage {

    suspend fun addNewCocktail(cocktailDto: CocktailDto)
    suspend fun deleteCocktail(cocktailDto: CocktailDto)
    suspend fun getAllCocktails(): List<CocktailDto>
    suspend fun getCocktail(id: Int): CocktailDto
    suspend fun editCocktail(cocktailDto: CocktailDto)
}