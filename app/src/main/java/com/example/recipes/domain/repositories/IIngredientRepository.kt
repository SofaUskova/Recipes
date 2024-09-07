package com.example.recipes.domain.repositories

import com.example.recipes.data.db.entities.IngredientEntity

interface IIngredientRepository {
    suspend fun insertOrUpdateIngredient(ingredient: IngredientEntity)
}