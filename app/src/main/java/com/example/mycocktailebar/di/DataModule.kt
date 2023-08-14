package com.example.mycocktailebar.di

import com.example.mycocktailebar.data.db.CocktailsDatabase
import com.example.mycocktailebar.data.db.RoomStorage
import com.example.mycocktailebar.data.db.impl.RoomStorageImpl
import com.example.mycocktailebar.data.repositories.StorageRepositoryImpl
import com.example.mycocktailebar.domain.repositories.StorageRepository
import org.koin.dsl.module

val dataModule = module {

    single<RoomStorage> { RoomStorageImpl(cocktailsDatabase = get()) }

    single<StorageRepository> { StorageRepositoryImpl(roomStorage = get()) }

    single<CocktailsDatabase> { CocktailsDatabase.getDataBase(context = get()) }
}