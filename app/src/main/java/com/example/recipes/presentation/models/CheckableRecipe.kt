package com.example.recipes.presentation.models

class CheckableRecipe(
    id: Long,
    photo: String,
    name: String,
    time: Float,
    ingredients: List<Ingredient>,
    recipe: String,
    var isChecked: Boolean
) : Recipe(id, photo, name, time, ingredients, recipe)

fun Recipe.toCheckableRecipe(): CheckableRecipe {
    return CheckableRecipe(id, photo, name, time, ingredients, recipe, isChecked = false)
}
