package com.example.mycocktailebar.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycocktailebar.data.db.dto.CocktailDto

@Dao
interface CocktailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewItem(cocktailDto: CocktailDto)

    @Update
    suspend fun editItem(cocktailDto: CocktailDto)

    @Delete
    suspend fun deleteItem(cocktailDto: CocktailDto)

    @Query("SELECT * FROM cocktails ORDER BY id ASC")
    suspend fun getAllCocktails(): List<CocktailDto>

    @Query("SELECT * FROM cocktails WHERE id =:id")
    suspend fun getCocktail(id: Int): CocktailDto


}