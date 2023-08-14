package com.example.mycocktailebar.presentation.creation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.storage.AddNewCocktailUseCase
import com.example.mycocktailebar.domain.storage.EditCocktailUseCase
import com.example.mycocktailebar.domain.storage.GetCocktailUseCase
import kotlinx.coroutines.launch

class CreationViewModel(
    private val addNewCocktailUseCase: AddNewCocktailUseCase,
    private val editCocktailUseCase: EditCocktailUseCase,
    private val getCocktailUseCase: GetCocktailUseCase
) : ViewModel() {

    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail: LiveData<Cocktail> = _cocktail

    private fun insertNewCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            addNewCocktailUseCase.execute(cocktail)
        }
    }

    fun addNewCocktail(
        name: String,
        description: String,
        ingredients: String,
        recipe: String
    ) {
        val newCocktail = Cocktail(
            id = 0,
            name = name,
            description = description,
            ingredients = ingredients,
            imageSrc = 0,
            recipe = recipe
        )
        insertNewCocktail(newCocktail)
    }

    fun isInputIsValid(
        name: String,
        ingredients: String,
    ) = (name.isNotBlank() && ingredients.isNotBlank())


    fun getCocktail(id: Int) {
        viewModelScope.launch {
            _cocktail.value = getCocktailUseCase.execute(id)
        }
    }

    private fun updateCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            editCocktailUseCase.execute(cocktail)
        }
    }

    fun editCocktail(
        id: Int,
        name: String,
        description: String,
        ingredients: String,
        recipe: String
    ) {
        val editedCocktail = Cocktail(
            id = id,
            name = name,
            description = description,
            ingredients = ingredients,
            imageSrc = 0,
            recipe = recipe
        )
        updateCocktail(editedCocktail)
    }

}