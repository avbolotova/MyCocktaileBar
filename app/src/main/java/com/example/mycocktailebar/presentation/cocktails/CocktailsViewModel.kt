package com.example.mycocktailebar.presentation.cocktails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktailebar.domain.models.Cocktail
import com.example.mycocktailebar.domain.storage.AddNewCocktailUseCase
import com.example.mycocktailebar.domain.storage.GetAllCocktailsUseCase
import com.example.mycocktailebar.presentation.cocktails.models.ScreenState
import kotlinx.coroutines.launch

class CocktailsViewModel(
    private val getAllCocktailsUseCase: GetAllCocktailsUseCase

) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _cocktailsList = MutableLiveData<List<Cocktail>>()
    val cocktailsList: LiveData<List<Cocktail>> = _cocktailsList


    fun getAllCocktails() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {

            try {
                _cocktailsList.value = getAllCocktailsUseCase.execute()

                _screenState.value =
                    if (cocktailsList.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content


            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }
        }
    }
}