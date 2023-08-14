package com.example.mycocktailebar.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycocktailebar.data.db.dto.CocktailDto

@Database(entities = [CocktailDto::class], version = 1, exportSchema = false)
abstract class CocktailsDatabase: RoomDatabase() {

    abstract fun cocktailDao(): CocktailsDao

    companion object {

        private var INSTANCE: CocktailsDatabase? = null

        fun getDataBase(context: Context): CocktailsDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CocktailsDatabase::class.java,
                    "cocktails_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}