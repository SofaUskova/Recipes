package com.example.recipes.repository

import com.example.recipes.db.dao.RecipeDao
import com.example.recipes.db.entities.RecipeWithIngredients
import com.example.recipes.repository.interfaces.IRecipeRepository
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.toRecipeEntity
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val dao: RecipeDao
) : IRecipeRepository {

    override suspend fun geRecipeWithIngredients(): List<RecipeWithIngredients>? {
        return dao.geRecipeWithIngredients()
    }

    override suspend fun insertOrUpdateRecipe(recipe: Recipe) {
        dao.insertOrUpdateRecipe(recipe.toRecipeEntity())
    }

    override suspend fun getRecipeById(id: Long): RecipeWithIngredients? {
        return dao.getRecipeById(id)
    }

    override suspend fun delete(recipe: Recipe?) {
        dao.delete(recipe?.toRecipeEntity())
    }
}