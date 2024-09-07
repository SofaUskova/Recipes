package com.example.recipes.domain

import com.example.recipes.domain.repositories.IIngredientRepository
import com.example.recipes.domain.repositories.IRecipeRepository
import com.example.recipes.presentation.models.Ingredient
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.models.toIngredientEntity
import com.example.recipes.presentation.models.toRecipe
import com.example.recipes.presentation.models.toRecipeEntity
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val repositoryRecipe: IRecipeRepository,
    private val repositoryIngredient: IIngredientRepository
) {

    suspend fun getListRecipe(): List<Recipe>? {
        return repositoryRecipe.geRecipeWithIngredients()?.map { it.toRecipe() }
    }

    suspend fun insertOrUpdateFullRecipe(recipe: Recipe, ingredients: List<Ingredient>) {
        repositoryRecipe.insertOrUpdateRecipe(recipe.toRecipeEntity())
        ingredients.forEach {
            repositoryIngredient.insertOrUpdateIngredient(it.toIngredientEntity(recipe.id))
        }
    }

    suspend fun getRecipeById(id: Long): Recipe? {
        return repositoryRecipe.getRecipeById(id)?.toRecipe()
    }

    suspend fun deleteRecipe(recipe: Recipe?) {
        repositoryRecipe.delete(recipe?.toRecipeEntity())
    }
}