package com.example.mycocktailebar.domain.storage

import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.repositories.StorageRepository

class GetCocktailUseCase(private val storageRepository: StorageRepository)  {

    suspend fun execute(id: Int): Cocktail = storageRepository.getCocktail(id)
}