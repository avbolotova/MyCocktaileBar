package com.example.mycocktailebar.domain.storage

import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.repositories.StorageRepository

class EditCocktailUseCase(private val storageRepository: StorageRepository)  {

    suspend fun execute(cocktail: Cocktail) = storageRepository.editCocktail(cocktail)
}