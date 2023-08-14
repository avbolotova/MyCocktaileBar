package com.example.mycocktailebar.di

import com.example.mycocktailebar.domain.storage.*
import org.koin.dsl.module

val domainModule = module {

    factory<AddNewCocktailUseCase> { AddNewCocktailUseCase(storageRepository = get()) }
    factory<DeleteCocktailUseCase> { DeleteCocktailUseCase(storageRepository = get()) }
    factory<EditCocktailUseCase> { EditCocktailUseCase(storageRepository = get()) }
    factory<GetAllCocktailsUseCase> { GetAllCocktailsUseCase(storageRepository = get()) }
    factory<GetCocktailUseCase> { GetCocktailUseCase(storageRepository = get()) }
}