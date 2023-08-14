package com.example.mycocktailebar.di

import com.example.mycocktailebar.presentation.cocktails.CocktailsViewModel
import com.example.mycocktailebar.presentation.creation.CreationViewModel
import com.example.mycocktailebar.presentation.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    viewModel<CocktailsViewModel> {
        CocktailsViewModel(
            getAllCocktailsUseCase = get()
        )
    }

    viewModel<CreationViewModel> {
        CreationViewModel(
            getCocktailUseCase = get(),
            addNewCocktailUseCase = get(),
            editCocktailUseCase = get()
        )
    }

    viewModel<DetailsViewModel> { DetailsViewModel(deleteCocktailUseCase = get()) }
}
