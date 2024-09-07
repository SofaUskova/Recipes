package com.example.recipes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.recipes.data.db.entities.IngredientEntity

@Dao
interface IngredientDao {

    fun insertOrUpdateIngredient(ingredient: IngredientEntity) {
        if (getIngredientById(ingredient.id) != null) {
            updateIngredient(ingredient)
        } else {
            insertIngredient(ingredient)
        }
    }

    @Transaction
    @Update
    fun updateIngredient(ingredient: IngredientEntity)

    @Transaction
    @Insert
    fun insertIngredient(ingredient: IngredientEntity)

    @Transaction
    @Query("SELECT * FROM ingredient WHERE ingredient.id == :id")
    fun getIngredientById(id: Long): IngredientEntity?

}