package com.example.recipes.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.recipes.db.entities.RecipeEntity
import com.example.recipes.db.entities.RecipeWithIngredients


@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe")
    fun geRecipeWithIngredients(): List<RecipeWithIngredients>?

    fun insertOrUpdateRecipe(recipe: RecipeEntity) {
        if (getRecipeById(recipe.id) != null) {
            updateRecipe(recipe)
        } else {
            insertRecipe(recipe)
        }
    }

    @Transaction
    @Insert
    fun insertRecipe(recipe: RecipeEntity)

    @Transaction
    @Update
    fun updateRecipe(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipe.id == :id")
    fun getRecipeById(id: Long): RecipeWithIngredients?

    @Transaction
    @Delete
    fun delete(recipe: RecipeEntity?)

}