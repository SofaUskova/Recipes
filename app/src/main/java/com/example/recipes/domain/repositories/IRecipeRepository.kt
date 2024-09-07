package com.example.recipes.domain.repositories

import com.example.recipes.data.db.entities.RecipeEntity
import com.example.recipes.data.db.entities.RecipeWithIngredientsEntity

interface IRecipeRepository {
    suspend fun geRecipeWithIngredients(): List<RecipeWithIngredientsEntity>?
    suspend fun insertOrUpdateRecipe(recipe: RecipeEntity)
    suspend fun getRecipeById(id: Long): RecipeWithIngredientsEntity?
    suspend fun delete(recipe: RecipeEntity?)
}