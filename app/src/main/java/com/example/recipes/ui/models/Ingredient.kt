package com.example.recipes.ui.models

import com.example.recipes.db.entities.IngredientEntity
import java.text.DecimalFormat

data class Ingredient(
    val id: Long,
    val text: String = "",
    val quantity: Float,
    val metric: Metric
) {

    override fun toString(): String {
        val formattedQuantity = DecimalFormat("#.#").format(quantity)
        return "$text $formattedQuantity${metric.abbreviation}"
    }
}

fun IngredientEntity.toIngredient(): Ingredient {
    return Ingredient(
        id = id,
        text= text,
        quantity = quantity,
        metric = Metric.GRAM
    )
}

fun Ingredient.toIngredientEntity(recipeId: Long): IngredientEntity {
    return IngredientEntity(
        id = id,
        text= text,
        quantity = quantity,
        metric = metric,
        recipeId = recipeId
    )
}

fun Ingredient.toRecipeIngredientItem(): RecipeIngredientItem {
    return RecipeIngredientItem(
        id = id,
        text = text,
        quantity = quantity,
        metric = metric
    )
}
