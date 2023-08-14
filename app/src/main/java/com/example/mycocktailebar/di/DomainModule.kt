package com.example.mycocktailebar.di

import com.example.mycocktailebar.domain.storage.AddNewCocktailUseCase
import com.example.mycocktailebar.domain.storage.DeleteCocktailUseCase
import com.example.mycocktailebar.domain.storage.EditCocktailUseCase
import com.example.mycocktailebar.domain.storage.GetAllCocktailsUseCase
import com.example.mycocktailebar.domain.storage.GetCocktailUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AddNewCocktailUseCase> { AddNewCocktailUseCase(storageRepository = get()) }
    factory<DeleteCocktailUseCase> { DeleteCocktailUseCase(storageRepository = get()) }
    factory<EditCocktailUseCase> { EditCocktailUseCase(storageRepository = get()) }
    factory<GetAllCocktailsUseCase> { GetAllCocktailsUseCase(storageRepository = get()) }
    factory<GetCocktailUseCase> { GetCocktailUseCase(storageRepository = get()) }
}