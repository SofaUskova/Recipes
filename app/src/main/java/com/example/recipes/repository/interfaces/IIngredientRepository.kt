package com.example.recipes.repository.interfaces

import com.example.recipes.ui.models.Ingredient

interface IIngredientRepository {
    suspend fun insertOrUpdateIngredient(ingredients: List<Ingredient>, recipeId: Long)
}