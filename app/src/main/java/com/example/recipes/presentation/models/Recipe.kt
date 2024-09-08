package com.example.recipes.presentation.models

import com.example.recipes.data.db.entities.RecipeEntity
import com.example.recipes.data.db.entities.RecipeWithIngredientsEntity
import java.text.DecimalFormat

open class Recipe(
    val id: Long,
    val photo: String,
    val name: String,
    val time: Float,
    val ingredients: List<Ingredient>,
    val recipe: String,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false
        if (photo != other.photo) return false
        if (name != other.name) return false
        if (time != other.time) return false
        if (ingredients != other.ingredients) return false
        if (recipe != other.recipe) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + photo.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + ingredients.hashCode()
        result = 31 * result + recipe.hashCode()
        return result
    }


}

fun RecipeWithIngredientsEntity.toRecipe(): Recipe {
    return Recipe(
        id = recipe.id,
        photo = recipe.photo,
        name = recipe.name,
        time = recipe.time,
        recipe = recipe.recipe,
        ingredients = ingredients.map { it.toIngredient() }
    )
}

fun Recipe.toRecipeEntity(menuTitle: String = " "): RecipeEntity {
    return RecipeEntity(
        id = id,
        photo = photo,
        name = name,
        time = time,
        recipe = recipe,
        menuTitle = menuTitle
    )
}

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        photo = photo,
        name = name,
        time = time,
        recipe = recipe,
        ingredients = listOf()
    )
}