package com.example.mycocktailebar.data.repositories

import com.example.mycocktailebar.data.db.RoomStorage
import com.example.mycocktailebar.data.db.dto.CocktailDto
import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.repositories.StorageRepository

class StorageRepositoryImpl(private val roomStorage: RoomStorage) : StorageRepository {


    override suspend fun addNewCocktail(cocktail: Cocktail) {
        val mappedCocktail = mapToData(cocktail)
        roomStorage.addNewCocktail(mappedCocktail)
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        val mappedCocktail = mapToData(cocktail)
        roomStorage.deleteCocktail(mappedCocktail)
    }

    override suspend fun getAllCocktails(): List<Cocktail> {
        val resultFromData = roomStorage.getAllCocktails()
        return resultFromData.map { cocktailDto ->
            mapToDomain(cocktailDto)
        }
    }

    override suspend fun getCocktail(id: Int): Cocktail {
        val resultFromData = roomStorage.getCocktail(id)
        return mapToDomain(resultFromData)
    }

    override suspend fun editCocktail(cocktail: Cocktail) {
        val mappedCocktail = mapToData(cocktail)
        roomStorage.editCocktail(mappedCocktail)
    }

    private fun mapToData(cocktail: Cocktail): CocktailDto {
        with(cocktail) {
            return CocktailDto(
                id = id,
                imageSrc = imageSrc,
                name = name,
                description = description,
                recipe = recipe,
                ingredients = ingredients,
            )
        }
    }

    private fun mapToDomain(cocktailDto: CocktailDto): Cocktail {
        with(cocktailDto) {
            return Cocktail(
                id = id,
                imageSrc = imageSrc,
                name = name,
                description = description,
                recipe = recipe,
                ingredients = ingredients,
            )
        }
    }

}