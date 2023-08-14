package com.example.mycocktailebar.domain.storage

import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.repositories.StorageRepository

class GetAllCocktailsUseCase(private val storageRepository: StorageRepository)  {

    suspend fun execute(): List<Cocktail> = storageRepository.getAllCocktails()
}