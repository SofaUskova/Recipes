package com.example.recipes.repository

import com.example.recipes.db.dao.IngredientDao
import com.example.recipes.repository.interfaces.IIngredientRepository
import com.example.recipes.ui.models.Ingredient
import com.example.recipes.ui.models.toIngredientEntity
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val dao: IngredientDao
) : IIngredientRepository {

    override suspend fun insertOrUpdateIngredient(ingredients: List<Ingredient>, recipeId: Long) {
        ingredients.forEach {
            dao.insertOrUpdateIngredient(it.toIngredientEntity(recipeId))
        }
    }
}