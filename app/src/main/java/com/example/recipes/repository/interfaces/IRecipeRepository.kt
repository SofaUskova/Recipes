package com.example.recipes.repository.interfaces

import com.example.recipes.db.entities.RecipeWithIngredients
import com.example.recipes.ui.models.Recipe

interface IRecipeRepository {
    suspend fun geRecipeWithIngredients(): List<RecipeWithIngredients>?
    suspend fun insertOrUpdateRecipe(recipe: Recipe)
    suspend fun getRecipeById(id: Long): RecipeWithIngredients?
    suspend fun delete(recipe: Recipe?)
}