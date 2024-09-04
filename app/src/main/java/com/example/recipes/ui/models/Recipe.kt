package com.example.recipes.ui.models

import com.example.recipes.db.entities.RecipeEntity
import com.example.recipes.db.entities.RecipeWithIngredients
import java.text.DecimalFormat

data class Recipe(
    val id: Long,
    val photo: String,
    val name: String,
    val time: Float,
    val ingredients: List<Ingredient>,
    val recipe: String
) {

    val formattedTime: String
        get() {
            val formattedTime = DecimalFormat("#.#").format(time)
            return "$formattedTime h"
        }

    val ingredientsByComma: String
        get() = ingredients.joinToString(", ")

    val ingredientsByDot: String
        get() = ingredients.joinToString(separator = "\n* ", prefix = "* ")
}

fun RecipeWithIngredients.toRecipe(): Recipe {
    return Recipe(
        id = recipe.id,
        photo = recipe.photo,
        name = recipe.name,
        time = recipe.time,
        recipe = recipe.recipe,
        ingredients = ingredients.map { it.toIngredient() }
    )
}

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        photo = photo,
        name = name,
        time = time,
        recipe = recipe,
    )
}