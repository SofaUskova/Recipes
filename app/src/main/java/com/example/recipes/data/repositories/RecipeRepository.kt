package com.example.recipes.data.repositories

import com.example.recipes.data.db.dao.RecipeDao
import com.example.recipes.data.db.entities.RecipeEntity
import com.example.recipes.data.db.entities.RecipeWithIngredientsEntity
import com.example.recipes.domain.repositories.IRecipeRepository
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val dao: RecipeDao
) : IRecipeRepository {

    override suspend fun geRecipeWithIngredients(): List<RecipeWithIngredientsEntity>? {
        return dao.geRecipeWithIngredients()
    }

    override suspend fun insertOrUpdateRecipe(recipe: RecipeEntity) {
        dao.insertOrUpdateRecipe(recipe)
    }

    override suspend fun getRecipeById(id: Long): RecipeWithIngredientsEntity? {
        return dao.getRecipeById(id)
    }

    override suspend fun delete(recipe: RecipeEntity?) {
        dao.delete(recipe)
    }

    override suspend fun updateRecipe(recipe: RecipeEntity) {
        dao.updateRecipe(recipe)
    }
}